package bcit.cst.service.impl;

import bcit.cst.pojo.Emp;
import bcit.cst.pojo.PageResult;
import bcit.cst.repository.EmpRepository;
import bcit.cst.service.EmpService;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhimeng Zheng
 * @version 2025-08-21
 */
@Service
public class EmpServiceImpl implements EmpService
{
    private final EmpRepository empRepository;

    public EmpServiceImpl(EmpRepository empRepository)
    {
        this.empRepository = empRepository;
    }

    @Override
    public PageResult<Emp> getEmployees(String name, String gender, LocalDate begin, LocalDate end, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);

        Specification<Emp> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null && !name.isEmpty()) {
                predicates.add(cb.like(root.get("name"), "%" + name + "%"));
            }
            if (gender != null && !gender.isEmpty()) {
                predicates.add(cb.equal(root.get("gender"), gender));
            }
            if (begin != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("hireDate"), begin));
            }
            if (end != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("hireDate"), end));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Page<Emp> empPage = empRepository.findAll(spec, pageable);

        return new PageResult<>(
                empPage.getTotalElements(),
                empPage.getContent()
        );
    }


}
