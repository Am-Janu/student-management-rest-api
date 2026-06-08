package com.janu.studentmanagement.controller;

import com.janu.studentmanagement.dto.EnrollmentRequestDto;
import com.janu.studentmanagement.dto.EnrollmentResponseDto;
import com.janu.studentmanagement.service.EnrollmentService;
import jakarta.validation.Valid;
import com.janu.studentmanagement.service.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public ResponseEntity<EnrollmentResponseDto> enrollStudent(@Valid @RequestBody EnrollmentRequestDto enrollmentRequestDto) {
        EnrollmentResponseDto createdEnrollment = enrollmentService.enrollStudent(enrollmentRequestDto);
        return new ResponseEntity<>(createdEnrollment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentResponseDto> getEnrollmentById(@PathVariable Long id) {
        EnrollmentResponseDto enrollmentResponseDto = enrollmentService.getEnrollmentById(id);
        return ResponseEntity.ok(enrollmentResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentResponseDto>> getAllEnrollments() {
        List<EnrollmentResponseDto> enrollments = enrollmentService.getAllEnrollments();
        return ResponseEntity.ok(enrollments);
    }

    @PutMapping("/{id}/grade")
    public ResponseEntity<EnrollmentResponseDto> updateGrade(@PathVariable Long id, @RequestParam String grade) {
        EnrollmentResponseDto updatedEnrollment = enrollmentService.updateGrade(id, grade);
        return ResponseEntity.ok(updatedEnrollment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> unenrollStudent(@PathVariable Long id) {
        enrollmentService.unenrollStudent(id);
        return ResponseEntity.ok("Student unenrolled successfully from course enrollment ID: " + id);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EnrollmentResponseDto>> getEnrollmentsByStudent(@PathVariable Long studentId) {
        List<EnrollmentResponseDto> enrollments = enrollmentService.getEnrollmentsByStudent(studentId);
        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<EnrollmentResponseDto>> getEnrollmentsByCourse(@PathVariable Long courseId) {
        List<EnrollmentResponseDto> enrollments = enrollmentService.getEnrollmentsByCourse(courseId);
        return ResponseEntity.ok(enrollments);
    }
}
