package bcit.cst.service.impl;

import bcit.cst.dto.EmpAddDTO;
import bcit.cst.dto.EmpDTO;
import bcit.cst.dto.EmpExprDTO;
import bcit.cst.pojo.*;
import bcit.cst.repository.DeptRepository;
import bcit.cst.repository.EmpExprRepository;
import bcit.cst.repository.EmpRepository;
import bcit.cst.service.EmpService;
import bcit.cst.util.SpecificationUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class EmpServiceImpl implements EmpService
{

    private final EmpRepository empRepository;
    private final DeptRepository deptRepository;
    private final EmpExprRepository empExprRepository;

    public EmpServiceImpl(EmpRepository empRepository, DeptRepository deptRepository, EmpExprRepository empExprRepository) {
        this.empRepository = empRepository;
        this.deptRepository = deptRepository;
        this.empExprRepository = empExprRepository;
    }

//    @Override
//    public PageResult<EmpDTO> getEmployees(String name, String gender, LocalDate begin, LocalDate end, int page, int pageSize) {
//        Pageable pageable = PageRequest.of(page - 1, pageSize);
//
//        Specification<Emp> spec = (root, query, cb) -> {
//            List<Predicate> predicates = new ArrayList<>();
//
//            if (name != null && !name.isEmpty()) {
//                predicates.add(cb.like(root.get("name"), "%" + name + "%"));
//            }
//            if (gender != null && !gender.isEmpty()) {
//                predicates.add(cb.equal(root.get("gender"), gender));
//            }
//            if (begin != null) {
//                predicates.add(cb.greaterThanOrEqualTo(root.get("entryDate"), begin));
//            }
//            if (end != null) {
//                predicates.add(cb.lessThanOrEqualTo(root.get("entryDate"), end));
//            }
//
//            return cb.and(predicates.toArray(new Predicate[0]));
//        };
//
//        Page<Emp> empPage = empRepository.findAll(spec, pageable);
//
//        // 批量查部门
//        List<Long> deptIds = empPage.stream()
//                .map(Emp::getDeptId)
//                .distinct()
//                .toList();
//
//        List<Dept> depts = deptRepository.findAllById(deptIds);
//        Map<Long, String> deptMap = depts.stream()
//                .collect(Collectors.toMap(Dept::getId, Dept::getName));
//
//        // 转换成 EmpDTO
//        List<EmpDTO> dtoList = empPage.stream().map(emp -> new EmpDTO(
//                emp.getId(),
//                emp.getUsername(),
//                emp.getName(),
//                emp.getGender(),
//                emp.getPhone(),
//                emp.getJob(),
//                emp.getSalary(),
//                emp.getImage(),
//                emp.getEntryDate(),
//                emp.getDeptId(),
//                deptMap.get(emp.getDeptId()), // ✅ 设置部门名
//                emp.getCreateTime(),
//                emp.getUpdateTime()
//        )).toList();
//
//        return new PageResult<>(
//                empPage.getTotalElements(),
//                dtoList
//        );
//    }


    @Override
    public PageResult<EmpDTO> getEmployees(EmpQueryParam param) {
        Pageable pageable = PageRequest.of(param.getPage() - 1, param.getPageSize());

        Specification<Emp> spec = SpecificationUtils.buildSpec(param);

        Page<Emp> empPage = empRepository.findAll(spec, pageable);

        // 批量查部门
        List<Long> deptIds = empPage.stream()
                .map(Emp::getDeptId)
                .distinct()
                .toList();

        List<Dept> depts = deptRepository.findAllById(deptIds);
        Map<Long, String> deptMap = depts.stream()
                .collect(Collectors.toMap(Dept::getId, Dept::getName));

        // 转换成 EmpDTO
        List<EmpDTO> dtoList = empPage.stream().map(emp -> new EmpDTO(
                emp.getId(),
                emp.getUsername(),
                emp.getName(),
                emp.getGender(),
                emp.getPhone(),
                emp.getJob(),
                emp.getSalary(),
                emp.getImage(),
                emp.getEntryDate(),
                emp.getDeptId(),
                deptMap.get(emp.getDeptId()), // ✅ 设置部门名
                emp.getCreateTime(),
                emp.getUpdateTime(),
                null // 工作经历暂时不加载
        )).toList();

        return new PageResult<>(
                empPage.getTotalElements(),
                dtoList
        );
    }

    @Override
    @Transactional
    public void add(EmpAddDTO empAddDTO)
    {
        // 1. 保存员工基本信息
        Emp emp = new Emp();
        emp.setUsername(empAddDTO.getUsername());
        emp.setName(empAddDTO.getName());
        emp.setGender(empAddDTO.getGender());
        emp.setPhone(empAddDTO.getPhone());
        emp.setJob(empAddDTO.getJob());
        emp.setSalary(empAddDTO.getSalary());
        emp.setImage(empAddDTO.getImage());
        emp.setEntryDate(empAddDTO.getEntryDate());
        emp.setDeptId(empAddDTO.getDeptId());
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());

        emp = empRepository.save(emp); // 保存后会自动生成 emp.id

//        int i = 1/0;
        // 2. 保存工作经历（如果有）
        if (empAddDTO.getExprList() != null && !empAddDTO.getExprList().isEmpty()) {
            Emp finalEmp = emp;
            List<EmpExpr> exprList = empAddDTO.getExprList().stream()
                    .map(exprDTO -> {
                        EmpExpr expr = new EmpExpr();
                        expr.setEmp(finalEmp); // ✅ 外键绑定：直接设置整个 Emp 对象
                        expr.setCompany(exprDTO.getCompany());
                        expr.setJob(exprDTO.getJob());
                        expr.setBegin(exprDTO.getBegin());
                        expr.setEnd(exprDTO.getEnd());
                        return expr;
                    })
                    .toList();

            empExprRepository.saveAll(exprList);
        }
    }

    @Override
    @Transactional
    public void deleteBatch(List<Long> ids)
    {
        // 先删子表
        empExprRepository.deleteByEmp_IdIn(ids);
        // 再删父表
        empRepository.deleteAllById(ids);
    }

    @Override
    public EmpDTO getById(Long id)
    {
        Emp emp = empRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("员工不存在"));

        // 查找子表
        List<EmpExpr> exprList = empExprRepository.findByEmp_Id(id);

        // 转换成 DTO
        EmpDTO dto = new EmpDTO();
        dto.setId(emp.getId());
        dto.setUsername(emp.getUsername());
        dto.setName(emp.getName());
        dto.setGender(emp.getGender());
        dto.setPhone(emp.getPhone());
        dto.setJob(emp.getJob());
        dto.setSalary(emp.getSalary());
        dto.setImage(emp.getImage()); // 存的 S3 key 或 URL
        dto.setEntryDate(emp.getEntryDate());
        dto.setDeptId(emp.getDeptId());

        // 子表转换
        List<EmpExprDTO> exprDTOs = exprList.stream().map(expr -> {
            EmpExprDTO e = new EmpExprDTO();
            e.setCompany(expr.getCompany());
            e.setJob(expr.getJob());
            e.setBegin(expr.getBegin());
            e.setEnd(expr.getEnd());
            return e;
        }).toList();

        dto.setExprList(exprDTOs);

        return dto;
    }
}

