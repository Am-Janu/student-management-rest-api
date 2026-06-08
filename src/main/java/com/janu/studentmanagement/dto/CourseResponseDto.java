package com.janu.studentmanagement.dto;

public class CourseResponseDto {
    private Long id;
    private String courseCode;
    private String title;
    private String description;
    private Integer credits;
    private DepartmentDto department;

    // Constructors
    public CourseResponseDto() {}

    public CourseResponseDto(Long id, String courseCode, String title, String description, Integer credits, DepartmentDto department) {
        this.id = id;
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.credits = credits;
        this.department = department;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getCredits() { return credits; }
    public void setCredits(Integer credits) { this.credits = credits; }

    public DepartmentDto getDepartment() { return department; }
    public void setDepartment(DepartmentDto department) { this.department = department; }

    // Builder
    public static CourseResponseDtoBuilder builder() {
        return new CourseResponseDtoBuilder();
    }

    public static class CourseResponseDtoBuilder {
        private Long id;
        private String courseCode;
        private String title;
        private String description;
        private Integer credits;
        private DepartmentDto department;

        public CourseResponseDtoBuilder id(Long id) { this.id = id; return this; }
        public CourseResponseDtoBuilder courseCode(String courseCode) { this.courseCode = courseCode; return this; }
        public CourseResponseDtoBuilder title(String title) { this.title = title; return this; }
        public CourseResponseDtoBuilder description(String description) { this.description = description; return this; }
        public CourseResponseDtoBuilder credits(Integer credits) { this.credits = credits; return this; }
        public CourseResponseDtoBuilder department(DepartmentDto department) { this.department = department; return this; }

        public CourseResponseDto build() {
            return new CourseResponseDto(id, courseCode, title, description, credits, department);
        }
    }
}
