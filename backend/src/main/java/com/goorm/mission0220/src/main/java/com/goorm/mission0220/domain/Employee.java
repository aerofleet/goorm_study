package com.goorm.mission0220.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "employees", indexes = {
    @Index(name = "idx_employees_name", columnList = "name"),
    @Index(name = "idx_employees_salary", columnList = "salary"),
    @Index(name = "idx_employees_hired_date", columnList = "hire_date")
})

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long salary;

    @CreatedDate
    @Column(name = "hire_date", nullable = false)
    private LocalDate hiredDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dpt_id")
    private Department department;

    @Column(length = 500)
    private String memo;

    @Builder
    public Employee(String name, Long salary, Department department, String memo) {
        this.name = name;
        this.salary = salary;
        this.department = department;
        this.memo = memo;
    }
}
