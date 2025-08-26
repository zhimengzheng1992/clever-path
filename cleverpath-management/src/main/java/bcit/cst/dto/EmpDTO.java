package bcit.cst.dto;
/**
 * @author Zhimeng Zheng
 * @version 2025-08-25
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpDTO {
    private Long id;
    private String username;
    private String name;
    private Integer gender;
    private String phone;
    private Integer job;
    private Integer salary;
    private String image;
    private LocalDate entryDate;

    private Long deptId;
    private String deptName; // ✅ 专门给前端返回部门名

    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private List<EmpExprDTO> exprList;
}
