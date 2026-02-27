package com.goorm.mission0220.repository;

import com.goorm.mission0220.domain.Employee;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.JoinType;

public class EmployeeSpecification {
    /**
     * 키워드로 name, memo, department.dptLoc 중 하나라도 포함하는 경우 검색
     */
    public static Specification<Employee> searchByKeyword(String keyword) {
        return (root, query, criteriaBuilder) -> {
            if (keyword == null || keyword.trim().isEmpty()) {
                return criteriaBuilder.conjunction(); // 조건 없음
            }

            String likePattern = "%" + keyword.trim() + "%";
            
            return criteriaBuilder.or(
                    criteriaBuilder.like(root.get("name"), likePattern),
                    criteriaBuilder.like(root.get("memo"), likePattern),
                    criteriaBuilder.like(root.join("department", JoinType.LEFT).get("dptLoc"), likePattern)
            );
        };
    }
}
