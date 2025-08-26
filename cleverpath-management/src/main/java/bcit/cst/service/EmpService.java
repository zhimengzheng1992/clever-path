package bcit.cst.service;

import bcit.cst.dto.EmpAddDTO;
import bcit.cst.dto.EmpDTO;
import bcit.cst.pojo.Emp;
import bcit.cst.pojo.EmpQueryParam;
import bcit.cst.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface EmpService
{
    PageResult<EmpDTO> getEmployees(EmpQueryParam empQueryParam);

    void add(EmpAddDTO empAddDTO);

    void deleteBatch(List<Long> ids);

    EmpDTO getById(Long id);


//    PageResult<EmpDTO> getEmployees(String name, Short gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize);

}
