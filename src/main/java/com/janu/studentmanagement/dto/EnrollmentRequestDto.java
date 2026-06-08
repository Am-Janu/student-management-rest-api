package com.janu.studentmanagement.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;

public class EnrollmentRequestDto {

    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "Course ID is required")
    private Long courseId;

    @NotNull(message = "Enrollment date is required")
    private LocalDate enrollmentDate;

    @Pattern(regexp = "^[A-DFa-df][+-]?$", message = "Grade must be a valid letter (A, B, C, D, F with optional + or -)")
    private String grade;

    // Constructors
    public EnrollmentRequestDto() {}

    public EnrollmentRequestDto(Long studentId, Long courseId, LocalDate enrollmentDate, String grade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.grade = grade;
    }

    // Getters and Setters
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }

    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(LocalDate enrollmentDate) { this.enrollmentDate = enrollmentDate; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    // Builder
    public static EnrollmentRequestDtoBuilder builder() {
        return new EnrollmentRequestDtoBuilder();
    }

    public static class EnrollmentRequestDtoBuilder {
        private Long studentId;
        private Long courseId;
        private LocalDate enrollmentDate;
        private String grade;

        public EnrollmentRequestDtoBuilder studentId(Long studentId) { this.studentId = studentId; return this; }
        public EnrollmentRequestDtoBuilder courseId(Long courseId) { this.courseId = courseId; return this; }
        public EnrollmentRequestDtoBuilder enrollmentDate(LocalDate enrollmentDate) { this.enrollmentDate = enrollmentDate; return this; }
        public EnrollmentRequestDtoBuilder grade(String grade) { this.grade = grade; return this; }

        public EnrollmentRequestDto build() {
            return new EnrollmentRequestDto(studentId, courseId, enrollmentDate, grade);
        }
    }
}
