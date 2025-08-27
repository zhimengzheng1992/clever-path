package bcit.cst.controller;

import bcit.cst.dto.EmpAddDTO;
import bcit.cst.dto.EmpDTO;
import bcit.cst.dto.EmpUpdateDTO;
import bcit.cst.pojo.EmpQueryParam;
import bcit.cst.pojo.PageResult;
import bcit.cst.pojo.Result;
import bcit.cst.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author Zhimeng Zheng
 * @version 2025-08-21
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    private final EmpService empService;

    public EmpController(EmpService empService) {
        this.empService = empService;
    }

//    @GetMapping
//    public Result<PageResult<EmpDTO>> list(
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) String gender,
//            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
//            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
//            @RequestParam(defaultValue = "1") int page,
//            @RequestParam(defaultValue = "10")  int pageSize) {
//        log.info("Pagination query for employees: {}, {}", page, pageSize);
//        PageResult<EmpDTO> pageResult = empService.getEmployees(name, gender, begin, end, page, pageSize);
//        return Result.success(pageResult);
//    }

    @GetMapping
    public Result<PageResult<EmpDTO>> list(EmpQueryParam empQueryParam) {
        log.info("Pagination query: {}", empQueryParam);
        PageResult<EmpDTO> pageResult = empService.getEmployees(empQueryParam);
        return Result.success(pageResult);
    }

    /**
     * add employee
     */
    @PostMapping
    public Result<Void> add(@RequestBody EmpAddDTO empAddDTO) {
        log.info("Adding new employee: {}", empAddDTO);
        empService.add(empAddDTO);
        return Result.success();
    }

    @DeleteMapping
    public Result<Void> delete(@RequestParam List<Long> ids) {
        log.info("Batch delete employees: {}", ids);
        empService.deleteBatch(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<EmpDTO> getById(@PathVariable Long id) {
        log.info("Fetching employee with id: {}", id);
        // Implementation to fetch employee by id
        EmpDTO empDTO = empService.getById(id);
        return Result.success(empDTO);
    }

    @PutMapping
    public Result<Void> update(@RequestBody EmpUpdateDTO empUpdateDTO) {
        log.info("Updating employee: {}", empUpdateDTO);
        // Implementation to update employee
        empService.update(empUpdateDTO);
        return Result.success();
    }
}
