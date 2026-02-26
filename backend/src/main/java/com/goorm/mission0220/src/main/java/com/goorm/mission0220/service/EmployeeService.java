package com.goorm.mission0220.service;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goorm.mission0220.domain.Department;
import com.goorm.mission0220.domain.Employee;
import com.goorm.mission0220.repository.DepartmentRepository;
import com.goorm.mission0220.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;


    @SuppressWarnings("null")
    public void executeMission() {
        // 1. 부서 및 사원 정보 저장
        Department dept = Department.builder()
                .dptName("Engineering")
                .dptLoc("Seoul")
                .build();
        dept = Objects.requireNonNull(departmentRepository.save(dept));

        Employee emp1 = Employee.builder()
                .name("아무개")
                .salary(60000L)
                .department(dept)
                .build();
        emp1 = Objects.requireNonNull(employeeRepository.save(emp1));

        // 2. 모든 사원 정보 검색 및 출력
        System.out.println("=== 전체 사원 목록 ===");
        employeeRepository.findAll().forEach(e -> 
            System.out.println("ID: " + e.getId() + ", 이름: " + e.getName() + ", 급여: " + e.getSalary()));

        // 3. 원하는 이름의 사원정보를 정렬하여 검색 및 출력
        System.out.println("=== 이름 검색 (아무개, 급여순) ===");
        employeeRepository.findByNameOrderBySalaryDesc("아무개").forEach(e ->
            System.out.println("검색결과: " + e.getName() + " / " + e.getSalary() + " / " + e.getDepartment().getDptName()));
    }

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
    public Employee addEmployee(String name, Long salary, Integer departmentId) {
        Department dept = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException("부서를 찾을 수 없습니다: " + departmentId));

        Employee employee = Employee.builder()
                .name(name)
                .salary(salary)
                .department(dept)
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
        employeeRepository.findAll().forEach(e ->
                System.out.println("ID: " + e.getId()
                        + ", 이름: " + e.getName()
                        + ", 급여: " + e.getSalary()
                        + ", 부서: " + (e.getDepartment() != null ? e.getDepartment().getDptName() : "-")
                        + ", 위치: " + (e.getDepartment() != null ? e.getDepartment().getDptLoc() : "-")));
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
}
