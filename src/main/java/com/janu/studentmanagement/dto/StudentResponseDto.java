package com.janu.studentmanagement.dto;

import java.time.LocalDate;

public class StudentResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private LocalDate enrollmentDate;
    private DepartmentDto department;

    // Constructors
    public StudentResponseDto() {}

    public StudentResponseDto(Long id, String firstName, String lastName, String email, LocalDate dateOfBirth, LocalDate enrollmentDate, DepartmentDto department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.enrollmentDate = enrollmentDate;
        this.department = department;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(LocalDate enrollmentDate) { this.enrollmentDate = enrollmentDate; }

    public DepartmentDto getDepartment() { return department; }
    public void setDepartment(DepartmentDto department) { this.department = department; }

    // Builder
    public static StudentResponseDtoBuilder builder() {
        return new StudentResponseDtoBuilder();
    }

    public static class StudentResponseDtoBuilder {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private LocalDate dateOfBirth;
        private LocalDate enrollmentDate;
        private DepartmentDto department;

        public StudentResponseDtoBuilder id(Long id) { this.id = id; return this; }
        public StudentResponseDtoBuilder firstName(String firstName) { this.firstName = firstName; return this; }
        public StudentResponseDtoBuilder lastName(String lastName) { this.lastName = lastName; return this; }
        public StudentResponseDtoBuilder email(String email) { this.email = email; return this; }
        public StudentResponseDtoBuilder dateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; return this; }
        public StudentResponseDtoBuilder enrollmentDate(LocalDate enrollmentDate) { this.enrollmentDate = enrollmentDate; return this; }
        public StudentResponseDtoBuilder department(DepartmentDto department) { this.department = department; return this; }

        public StudentResponseDto build() {
            return new StudentResponseDto(id, firstName, lastName, email, dateOfBirth, enrollmentDate, department);
        }
    }
}
