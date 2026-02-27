package com.goorm.mission0220.service;

import java.util.Objects;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goorm.mission0220.domain.Department;
import com.goorm.mission0220.domain.Employee;
import com.goorm.mission0220.repository.DepartmentRepository;
import com.goorm.mission0220.repository.EmployeeRepository;
import com.goorm.mission0220.repository.EmployeeSpecification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    /**
     * 신규 부서를 생성하고 저장합니다.
     */
    @SuppressWarnings("null")
    public Department addDepartment(String name, String location) {
        Department dept = Department.builder()
                .dptName(name)
                .dptLoc(location)
                .build();
        return Objects.requireNonNull(departmentRepository.save(dept));
    }

    /**
     * 기존 부서에 소속된 사원을 생성하고 저장합니다.
     * @param departmentId 저장할 부서 ID (존재하지 않으면 IllegalArgumentException 발생)
     */
    @SuppressWarnings("null")
    public Employee addEmployee(String name, Long salary, Integer departmentId, String memo) {
        Department dept = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException("부서를 찾을 수 없습니다: " + departmentId));

        Employee employee = Employee.builder()
                .name(name)
                .salary(salary)
                .department(dept)
                .memo(memo != null ? memo : "")
                .build();

        return Objects.requireNonNull(employeeRepository.save(employee));
    }

    /**
     * 부서명이 이미 존재하면 재사용하고, 없으면 새로 생성합니다.
     */
    public Department getOrCreateDepartment(String name, String location) {
        return departmentRepository.findByDptName(name)
                .orElseGet(() -> addDepartment(name, location));
    }
    

    /** 모든 사원 정보를 콘솔에 출력 */
    public void printAllEmployees() {
        System.out.println("=== 전체 사원 목록 ===");
        System.out.flush(); 
        List<Employee> list = employeeRepository.findAll();
        list.stream().sorted((e1, e2) -> e2.getSalary().compareTo(e1.getSalary())).forEach(e -> 
                System.out.println("ID: " + e.getId()
                        + ", 이름: " + e.getName()
                        + ", 급여: " + e.getSalary()
                        + ", 부서: " + (e.getDepartment() != null ? e.getDepartment().getDptName() : "-")
                        + ", 위치: " + (e.getDepartment() != null ? e.getDepartment().getDptLoc() : "-")
                        + ", 메모: " + (e.getMemo() != null ? e.getMemo() : "-")));
    }

    /** 이름으로 검색 후 급여 내림차순 출력 */
    public void printEmployeesByName(String name) {
        System.out.println("=== 이름 검색 (" + name + ", 급여순) ===");
        employeeRepository.findByNameOrderBySalaryDesc(name).forEach(e ->
                System.out.println("검색결과: " + e.getName()
                        + " / " + e.getSalary()
                        + " / " + (e.getDepartment() != null ? e.getDepartment().getDptName() : "-")
                        + " / " + (e.getDepartment() != null ? e.getDepartment().getDptLoc() : "-")));
    }

    /**
     * 키워드로 name(작성자), memo(내용), dptLoc(제목)을 검색하여 급여 내림차순으로 출력
     */
    public void printEmployeesByKeyword(String keyword) {
        System.out.println("=== 키워드 검색 결과 ('" + keyword + "') ===");
        
        List<Employee> results = employeeRepository.findAll(
            EmployeeSpecification.searchByKeyword(keyword),
            Sort.by(Sort.Direction.DESC, "salary")
        );
        
        if (results.isEmpty()) {
            System.out.println("검색 결과가 없습니다.");
            return;
        }
        
        results.forEach(e -> {
            System.out.println("ID: " + e.getId()
                    + ", 이름: " + e.getName()
                    + ", 급여: " + e.getSalary()
                    + ", 부서: " + (e.getDepartment() != null ? e.getDepartment().getDptName() : "-")
                    + ", 위치: " + (e.getDepartment() != null ? e.getDepartment().getDptLoc() : "-")
                    + ", 메모: " + (e.getMemo() != null && !e.getMemo().isEmpty() ? e.getMemo() : "-"));
        });
        
        System.out.println("총 " + results.size() + "건의 결과가 검색되었습니다.");
    }


}
