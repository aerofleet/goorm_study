package com.goorm.mission0220.repository;

import com.goorm.mission0220.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Optional<Department> findByDptName(String dptName);
}
