package bcit.cst.pojo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "emp_expr")
public class EmpExpr {

    // --- Getter/Setter ---
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 对应 PostgreSQL 的 serial
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id", nullable = false)
    private Emp emp;  // 外键关联 Emp 表

    @Column(name = "begin_date")
    private LocalDate begin; // 开始时间

    @Column(name = "end_date")
    private LocalDate end; // 结束时间

    @Column(name = "company", length = 50)
    private String company; // 公司名称

    @Column(name = "job", length = 50)
    private String job; // 职位

}

