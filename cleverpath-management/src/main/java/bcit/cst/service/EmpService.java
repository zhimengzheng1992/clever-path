package bcit.cst.service;

import bcit.cst.pojo.Emp;
import bcit.cst.pojo.PageResult;

import java.time.LocalDate;

public interface EmpService
{
    PageResult<Emp> getEmployees(
            String name,
            String gender,
            LocalDate begin,
            LocalDate end,
            int page,
            int pageSize
    );
}
