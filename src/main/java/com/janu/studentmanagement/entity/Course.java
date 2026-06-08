package com.janu.studentmanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Course code is required")
    @Column(name = "course_code", nullable = false, unique = true)
    private String courseCode;

    @NotBlank(message = "Course title is required")
    @Column(nullable = false)
    private String title;

    @Column(length = 500)
    private String description;

    @NotNull(message = "Course credits is required")
    @Min(value = 1, message = "Credits must be at least 1")
    @Max(value = 6, message = "Credits cannot exceed 6")
    private Integer credits;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    // Constructors
    public Course() {}

    public Course(Long id, String courseCode, String title, String description, Integer credits, Department department) {
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

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    // Builder
    public static CourseBuilder builder() {
        return new CourseBuilder();
    }

    public static class CourseBuilder {
        private Long id;
        private String courseCode;
        private String title;
        private String description;
        private Integer credits;
        private Department department;

        public CourseBuilder id(Long id) { this.id = id; return this; }
        public CourseBuilder courseCode(String courseCode) { this.courseCode = courseCode; return this; }
        public CourseBuilder title(String title) { this.title = title; return this; }
        public CourseBuilder description(String description) { this.description = description; return this; }
        public CourseBuilder credits(Integer credits) { this.credits = credits; return this; }
        public CourseBuilder department(Department department) { this.department = department; return this; }

        public Course build() {
            return new Course(id, courseCode, title, description, credits, department);
        }
    }
}
