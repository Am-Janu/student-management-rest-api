package com.janu.studentmanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class StudentRequestDto {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    @NotNull(message = "Enrollment date is required")
    private LocalDate enrollmentDate;

    @NotNull(message = "Department ID is required")
    private Long departmentId;

    // Constructors
    public StudentRequestDto() {}

    public StudentRequestDto(String firstName, String lastName, String email, LocalDate dateOfBirth, LocalDate enrollmentDate, Long departmentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.enrollmentDate = enrollmentDate;
        this.departmentId = departmentId;
    }

    // Getters and Setters
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

    public Long getDepartmentId() { return departmentId; }
    public void setDepartmentId(Long departmentId) { this.departmentId = departmentId; }

    // Builder
    public static StudentRequestDtoBuilder builder() {
        return new StudentRequestDtoBuilder();
    }

    public static class StudentRequestDtoBuilder {
        private String firstName;
        private String lastName;
        private String email;
        private LocalDate dateOfBirth;
        private LocalDate enrollmentDate;
        private Long departmentId;

        public StudentRequestDtoBuilder firstName(String firstName) { this.firstName = firstName; return this; }
        public StudentRequestDtoBuilder lastName(String lastName) { this.lastName = lastName; return this; }
        public StudentRequestDtoBuilder email(String email) { this.email = email; return this; }
        public StudentRequestDtoBuilder dateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; return this; }
        public StudentRequestDtoBuilder enrollmentDate(LocalDate enrollmentDate) { this.enrollmentDate = enrollmentDate; return this; }
        public StudentRequestDtoBuilder departmentId(Long departmentId) { this.departmentId = departmentId; return this; }

        public StudentRequestDto build() {
            return new StudentRequestDto(firstName, lastName, email, dateOfBirth, enrollmentDate, departmentId);
        }
    }
}
