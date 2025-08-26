package bcit.cst.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @author Zhimeng Zheng
 * @version 2025-08-25
 */
@Data
public class EmpQueryParam
{
    private Integer page = 1;       // 当前页码, 默认第一页
    private Integer pageSize = 10;  // 每页显示的记录数, 默认10
    private String name;            // 员工姓名, 模糊查询
    private Short gender;         // 员工性别, 精确查询
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;
}
