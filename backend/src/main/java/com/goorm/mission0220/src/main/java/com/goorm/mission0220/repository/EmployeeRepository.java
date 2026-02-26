package com.goorm.mission0220.repository;

import com.goorm.mission0220.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    List<Employee> findByNameOrderBySalaryDesc(String name);
}
