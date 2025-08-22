package bcit.cst.repository;

import bcit.cst.pojo.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpRepository extends JpaRepository<Emp, Integer>, JpaSpecificationExecutor<Emp>
{
}
