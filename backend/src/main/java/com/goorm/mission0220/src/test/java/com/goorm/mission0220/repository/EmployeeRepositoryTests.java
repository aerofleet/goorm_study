package com.goorm.mission0220.repository;

import com.goorm.mission0220.domain.Employee;
import com.goorm.mission0220.domain.Department;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
// import java.time.LocalDate; // (삭제) 사용하지 않는 임포트 제거

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class EmployeeRepositoryTests {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    @DisplayName("이름으로 직원 검색 및 급여 내림차순 정렬")
    public void testFindByNameOrderBySalaryDesc() {
        // Given
        Department department = Department.builder()
                .dptName("Engineering")
                .dptLoc("Seoul")
                .build();
        
        // saveAndFlush의 결과를 받을 때 null이 아님을 보장하거나 경고 무시
        Department saveDept = departmentRepository.saveAndFlush(department);
        assert saveDept != null; // 명시적 검증 추가

        Employee emp1 = Employee.builder()
                .name("John Doe")
                .salary(50000L)
                .department(saveDept)
                .build();
        Employee emp2 = Employee.builder()
                .name("John Doe")
                .salary(60000L)
                .department(saveDept)
                .build();
        Employee emp3 = Employee.builder()
                .name("Jane Smith")
                .salary(55000L)
                .department(saveDept)
                .build();

        // save() 결과에 대한 Null Safety 경고는 테스트 코드에서는 대개 무시해도 무방하나,
        // 필요 시 지역 변수에 담지 않고 바로 호출하여 경고를 줄일 수 있습니다.
        employeeRepository.save(emp1);
        employeeRepository.save(emp2);
        employeeRepository.save(emp3);

        // When
        List<Employee> result = employeeRepository.findByNameOrderBySalaryDesc("John Doe");

        // Then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getSalary()).isGreaterThan(result.get(1).getSalary());
        assertThat(result.get(0).getDepartment().getDptName()).isEqualTo("Engineering");
    }
}