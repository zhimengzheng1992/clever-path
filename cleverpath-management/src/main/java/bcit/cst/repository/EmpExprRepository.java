package bcit.cst.repository;

import bcit.cst.pojo.EmpExpr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmpExprRepository extends JpaRepository<EmpExpr, Integer> {

    // 根据员工ID查找经历
    List<EmpExpr> findByEmpId(Integer empId);

    // 根据公司名查找
    List<EmpExpr> findByCompanyContaining(String keyword);

    // 根据起止时间查询
    List<EmpExpr> findByBeginDateBetween(LocalDate start, LocalDate end);

    List<EmpExpr> findByEndDateBetween(LocalDate start, LocalDate end);
}
