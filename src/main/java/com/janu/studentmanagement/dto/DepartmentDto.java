package com.janu.studentmanagement.dto;

import jakarta.validation.constraints.NotBlank;

public class DepartmentDto {
    private Long id;

    @NotBlank(message = "Department name is required")
    private String name;

    @NotBlank(message = "Department code is required")
    private String code;

    private String headOfDepartment;

    // Constructors
    public DepartmentDto() {}

    public DepartmentDto(Long id, String name, String code, String headOfDepartment) {
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

    // Builder
    public static DepartmentDtoBuilder builder() {
        return new DepartmentDtoBuilder();
    }

    public static class DepartmentDtoBuilder {
        private Long id;
        private String name;
        private String code;
        private String headOfDepartment;

        public DepartmentDtoBuilder id(Long id) { this.id = id; return this; }
        public DepartmentDtoBuilder name(String name) { this.name = name; return this; }
        public DepartmentDtoBuilder code(String code) { this.code = code; return this; }
        public DepartmentDtoBuilder headOfDepartment(String headOfDepartment) { this.headOfDepartment = headOfDepartment; return this; }

        public DepartmentDto build() {
            return new DepartmentDto(id, name, code, headOfDepartment);
        }
    }
}
