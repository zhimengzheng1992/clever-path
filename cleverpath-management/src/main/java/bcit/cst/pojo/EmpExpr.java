package bcit.cst.pojo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Table(name = "emp_expr")
public class EmpExpr {

    // --- Getter/Setter ---
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 对应 PostgreSQL 的 serial
    private Integer id;

    @Column(name = "emp_id", nullable = false)
    private Integer empId; // 外键 -> emp.id

    @Column(name = "begin")
    private LocalDate beginDate; // 开始时间

    @Column(name = "end")
    private LocalDate endDate; // 结束时间

    @Column(name = "company", length = 50)
    private String company; // 公司名称

    @Column(name = "job", length = 50)
    private String job; // 职位



    public void setBegin(LocalDate begin) {
        this.beginDate = begin;
    }

    public void setEnd(LocalDate end) {
        this.endDate = end;
    }


}

