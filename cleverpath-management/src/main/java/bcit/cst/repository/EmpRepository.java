package bcit.cst.repository;

import bcit.cst.pojo.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpRepository extends JpaRepository<Emp, Long>, JpaSpecificationExecutor<Emp> {
    @Query("SELECT e.job, COUNT(e) FROM Emp e GROUP BY e.job")
    List<Object[]> countByJob();

    @Query("SELECT e.gender, COUNT(e) FROM Emp e GROUP BY e.gender")
    List<Object[]> countByGender();

    Optional<Emp> findByUsername(String username);
}
