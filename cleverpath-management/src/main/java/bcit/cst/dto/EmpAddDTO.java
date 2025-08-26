package bcit.cst.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Zhimeng Zheng
 * @version 2025-08-25
 */
@Data
public class EmpAddDTO {
    private String username;
    private String name;
    private Short gender;
    private String phone;
    private Short job;
    private Integer salary;
    private String image;
    private LocalDate entryDate;
    private Long deptId;

    private List<EmpExprDTO> exprList; // 工作经历
}
