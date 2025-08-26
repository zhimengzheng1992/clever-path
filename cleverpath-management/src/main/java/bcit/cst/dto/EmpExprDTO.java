package bcit.cst.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author Zhimeng Zheng
 * @version 2025-08-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpExprDTO {
    private String company;
    private String job;
    private LocalDate begin;
    private LocalDate end;
}

