package com.janu.studentmanagement.dto;

import java.time.LocalDate;

public class EnrollmentResponseDto {
    private Long id;
    private StudentResponseDto student;
    private CourseResponseDto course;
    private LocalDate enrollmentDate;
    private String grade;

    // Constructors
    public EnrollmentResponseDto() {}

    public EnrollmentResponseDto(Long id, StudentResponseDto student, CourseResponseDto course, LocalDate enrollmentDate, String grade) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
        this.grade = grade;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public StudentResponseDto getStudent() { return student; }
    public void setStudent(StudentResponseDto student) { this.student = student; }

    public CourseResponseDto getCourse() { return course; }
    public void setCourse(CourseResponseDto course) { this.course = course; }

    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(LocalDate enrollmentDate) { this.enrollmentDate = enrollmentDate; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    // Builder
    public static EnrollmentResponseDtoBuilder builder() {
        return new EnrollmentResponseDtoBuilder();
    }

    public static class EnrollmentResponseDtoBuilder {
        private Long id;
        private StudentResponseDto student;
        private CourseResponseDto course;
        private LocalDate enrollmentDate;
        private String grade;

        public EnrollmentResponseDtoBuilder id(Long id) { this.id = id; return this; }
        public EnrollmentResponseDtoBuilder student(StudentResponseDto student) { this.student = student; return this; }
        public EnrollmentResponseDtoBuilder course(CourseResponseDto course) { this.course = course; return this; }
        public EnrollmentResponseDtoBuilder enrollmentDate(LocalDate enrollmentDate) { this.enrollmentDate = enrollmentDate; return this; }
        public EnrollmentResponseDtoBuilder grade(String grade) { this.grade = grade; return this; }

        public EnrollmentResponseDto build() {
            return new EnrollmentResponseDto(id, student, course, enrollmentDate, grade);
        }
    }
}
