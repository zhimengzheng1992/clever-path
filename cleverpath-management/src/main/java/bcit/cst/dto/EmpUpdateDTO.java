package bcit.cst.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Zhimeng Zheng
 * @version 2025-08-26
 */
@Data
public class EmpUpdateDTO {
    private Long id;
    private String username;
    private String name;
    private Integer gender;
    private String phone;
    private Integer job;
    private Integer salary;
    private String image; // S3 key
    private LocalDate entryDate;
    private Long deptId;
    private List<EmpExprDTO> exprList;
}

