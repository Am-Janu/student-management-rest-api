package com.janu.studentmanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Department name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Department code is required")
    @Column(nullable = false, unique = true)
    private String code;

    @Column(name = "head_of_department")
    private String headOfDepartment;

    // Constructors
    public Department() {}

    public Department(Long id, String name, String code, String headOfDepartment) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.headOfDepartment = headOfDepartment;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getHeadOfDepartment() { return headOfDepartment; }
    public void setHeadOfDepartment(String headOfDepartment) { this.headOfDepartment = headOfDepartment; }

    // Builder Pattern
    public static DepartmentBuilder builder() {
        return new DepartmentBuilder();
    }

    public static class DepartmentBuilder {
        private Long id;
        private String name;
        private String code;
        private String headOfDepartment;

        public DepartmentBuilder id(Long id) { this.id = id; return this; }
        public DepartmentBuilder name(String name) { this.name = name; return this; }
        public DepartmentBuilder code(String code) { this.code = code; return this; }
        public DepartmentBuilder headOfDepartment(String headOfDepartment) { this.headOfDepartment = headOfDepartment; return this; }

        public Department build() {
            return new Department(id, name, code, headOfDepartment);
        }
    }
}
