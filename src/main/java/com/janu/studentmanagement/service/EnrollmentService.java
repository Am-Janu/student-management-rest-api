package com.janu.studentmanagement.service;

import com.janu.studentmanagement.dto.EnrollmentRequestDto;
import com.janu.studentmanagement.dto.EnrollmentResponseDto;

import java.util.List;

public interface EnrollmentService {
    EnrollmentResponseDto enrollStudent(EnrollmentRequestDto enrollmentRequestDto);
    EnrollmentResponseDto updateGrade(Long id, String grade);
    void unenrollStudent(Long id);
    List<EnrollmentResponseDto> getEnrollmentsByStudent(Long studentId);
    List<EnrollmentResponseDto> getEnrollmentsByCourse(Long courseId);
    List<EnrollmentResponseDto> getAllEnrollments();
    EnrollmentResponseDto getEnrollmentById(Long id);
}
