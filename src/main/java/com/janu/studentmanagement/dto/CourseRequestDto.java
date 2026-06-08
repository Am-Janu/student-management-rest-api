package com.janu.studentmanagement.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CourseRequestDto {

    @NotBlank(message = "Course code is required")
    private String courseCode;

    @NotBlank(message = "Course title is required")
    private String title;

    private String description;

    @NotNull(message = "Course credits is required")
    @Min(value = 1, message = "Credits must be at least 1")
    @Max(value = 6, message = "Credits cannot exceed 6")
    private Integer credits;

    @NotNull(message = "Department ID is required")
    private Long departmentId;

    // Constructors
    public CourseRequestDto() {}

    public CourseRequestDto(String courseCode, String title, String description, Integer credits, Long departmentId) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.credits = credits;
        this.departmentId = departmentId;
    }

    // Getters and Setters
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getCredits() { return credits; }
    public void setCredits(Integer credits) { this.credits = credits; }

    public Long getDepartmentId() { return departmentId; }
    public void setDepartmentId(Long departmentId) { this.departmentId = departmentId; }

    // Builder
    public static CourseRequestDtoBuilder builder() {
        return new CourseRequestDtoBuilder();
    }

    public static class CourseRequestDtoBuilder {
        private String courseCode;
        private String title;
        private String description;
        private Integer credits;
        private Long departmentId;

        public CourseRequestDtoBuilder courseCode(String courseCode) { this.courseCode = courseCode; return this; }
        public CourseRequestDtoBuilder title(String title) { this.title = title; return this; }
        public CourseRequestDtoBuilder description(String description) { this.description = description; return this; }
        public CourseRequestDtoBuilder credits(Integer credits) { this.credits = credits; return this; }
        public CourseRequestDtoBuilder departmentId(Long departmentId) { this.departmentId = departmentId; return this; }

        public CourseRequestDto build() {
            return new CourseRequestDto(courseCode, title, description, credits, departmentId);
        }
    }
}
