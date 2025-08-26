package bcit.cst.pojo;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "emp") // 数据库里的表名
public class Emp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 主键自增
    private Long id;          // ID, 主键
    private String username;     // 用户名
    private String password;     // 密码
    private String name;         // 姓名
    private Short gender;        // 性别, 1:男, 2:女
    private String phone;        // 手机号
    private Short job;           // 职位
    private Integer salary;      // 薪资

    @Column(length = 1000)
    private String image;        // 头像

    private LocalDate entryDate; // 入职日期 -> 自动映射 entry_date
    private Long deptId;      // 部门ID -> 自动映射 dept_id
    private LocalDateTime createTime; // 创建时间 -> 自动映射 create_time
    private LocalDateTime updateTime; // 修改时间 -> 自动映射 update_time

}
