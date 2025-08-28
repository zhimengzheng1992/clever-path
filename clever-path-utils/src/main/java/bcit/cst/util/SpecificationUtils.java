package bcit.cst.util;

import bcit.cst.pojo.Emp;
import bcit.cst.pojo.EmpQueryParam;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SpecificationUtils {

    /**
     * 根据 EmpQueryParam 构建动态查询条件
     */
    public static Specification<Emp> buildSpec(EmpQueryParam param) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (param.getName() != null && !param.getName().isEmpty()) {
                predicates.add(cb.like(root.get("name"), "%" + param.getName() + "%"));
            }
            if (param.getGender() != null) {
                predicates.add(cb.equal(root.get("gender"), param.getGender()));
            }
            if (param.getBegin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("entryDate"), param.getBegin()));
            }
            if (param.getEnd() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("entryDate"), param.getEnd()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
