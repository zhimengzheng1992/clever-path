package bcit.cst.controller;

import bcit.cst.pojo.Emp;
import bcit.cst.pojo.PageResult;
import bcit.cst.pojo.Result;
import bcit.cst.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * @author Zhimeng Zheng
 * @version 2025-08-21
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController
{
    private final EmpService empService;

    public EmpController(EmpService empService)
    {
        this.empService = empService;
    }

    @GetMapping
    public Result<PageResult<Emp>> list(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize) {
        PageResult<Emp> pageResult = empService.getEmployees(name, gender, begin, end, page, pageSize);
        return Result.success(pageResult);
    }

}
