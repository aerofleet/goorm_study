package com.goorm.mission0220.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;
import java.util.List;
import java.util.ArrayList;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "departments", indexes = {
    @Index(name = "idx_departments_name", columnList = "dpt_name")
})
public class Department {
    @Id
    @GeneratedValue
    @Column(name = "dpt_id")
    private Integer dptId;

    @Column(name = "dpt_name", nullable = false)
    private String dptName;

    @Column(name = "dpt_loc", nullable = false)
    private String dptLoc;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees = new ArrayList<>();

    @Builder
    public Department(Integer dptId, String dptName, String dptLoc) {
        this.dptId = dptId;
        this.dptName = dptName;
        this.dptLoc = dptLoc;
    }
}
