package com.janu.studentmanagement.controller;

import com.janu.studentmanagement.dto.CourseRequestDto;
import com.janu.studentmanagement.dto.CourseResponseDto;
import com.janu.studentmanagement.service.CourseService;
import jakarta.validation.Valid;
import com.janu.studentmanagement.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<CourseResponseDto> createCourse(@Valid @RequestBody CourseRequestDto courseRequestDto) {
        CourseResponseDto createdCourse = courseService.createCourse(courseRequestDto);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDto> getCourseById(@PathVariable Long id) {
        CourseResponseDto courseResponseDto = courseService.getCourseById(id);
        return ResponseEntity.ok(courseResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> getAllCourses() {
        List<CourseResponseDto> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDto> updateCourse(@PathVariable Long id, @Valid @RequestBody CourseRequestDto courseRequestDto) {
        CourseResponseDto updatedCourse = courseService.updateCourse(id, courseRequestDto);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok("Course deleted successfully with ID: " + id);
    }

    @GetMapping("/department/{deptId}")
    public ResponseEntity<List<CourseResponseDto>> getCoursesByDepartment(@PathVariable Long deptId) {
        List<CourseResponseDto> courses = courseService.getCoursesByDepartment(deptId);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/credits/{credits}")
    public ResponseEntity<List<CourseResponseDto>> getCoursesByCredits(@PathVariable Integer credits) {
        List<CourseResponseDto> courses = courseService.getCoursesByCredits(credits);
        return ResponseEntity.ok(courses);
    }
}
