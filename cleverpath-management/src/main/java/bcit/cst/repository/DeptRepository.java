package bcit.cst.repository;

import bcit.cst.pojo.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptRepository extends JpaRepository<Dept, Long> {
    // JpaRepository<实体类, 主键类型>
    // 已经包含了常见方法：save, findById, findAll, deleteById 等

    // 你也可以自定义方法，比如根据部门名查询
    Dept findByName(String name);

    List<Dept> findAllByOrderByUpdateTimeDesc();
}
