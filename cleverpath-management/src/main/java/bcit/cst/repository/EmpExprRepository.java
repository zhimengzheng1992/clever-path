package bcit.cst.repository;

import bcit.cst.pojo.EmpExpr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmpExprRepository extends JpaRepository<EmpExpr, Long> {

    // 根据员工ID查找经历
    List<EmpExpr> findByEmp_Id(Long empId);

    // 根据公司名查找
    List<EmpExpr> findByCompanyContaining(String keyword);

    // 根据起止时间查询
    List<EmpExpr> findByBeginBetween(LocalDate start, LocalDate end);

    List<EmpExpr> findByEndBetween(LocalDate start, LocalDate end);

    void deleteByEmp_IdIn(List<Long> ids);

}
