package com.janu.studentmanagement.service.impl;

import com.janu.studentmanagement.dto.*;
import com.janu.studentmanagement.entity.Course;
import com.janu.studentmanagement.entity.Enrollment;
import com.janu.studentmanagement.entity.Student;
import com.janu.studentmanagement.exception.DuplicateResourceException;
import com.janu.studentmanagement.exception.ResourceNotFoundException;
import com.janu.studentmanagement.repository.CourseRepository;
import com.janu.studentmanagement.repository.EnrollmentRepository;
import com.janu.studentmanagement.repository.StudentRepository;
import com.janu.studentmanagement.service.EnrollmentService;
import com.janu.studentmanagement.repository.CourseRepository;
import com.janu.studentmanagement.repository.EnrollmentRepository;
import com.janu.studentmanagement.repository.StudentRepository;
import com.janu.studentmanagement.service.EnrollmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional
    public EnrollmentResponseDto enrollStudent(EnrollmentRequestDto enrollmentRequestDto) {
        if (enrollmentRepository.existsByStudentIdAndCourseId(enrollmentRequestDto.getStudentId(), enrollmentRequestDto.getCourseId())) {
            throw new DuplicateResourceException("Student is already enrolled in this course.");
        }

        Student student = studentRepository.findById(enrollmentRequestDto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + enrollmentRequestDto.getStudentId()));

        Course course = courseRepository.findById(enrollmentRequestDto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + enrollmentRequestDto.getCourseId()));

        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .enrollmentDate(enrollmentRequestDto.getEnrollmentDate())
                .grade(enrollmentRequestDto.getGrade() != null ? enrollmentRequestDto.getGrade().toUpperCase() : null)
                .build();

        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        return mapToDto(savedEnrollment);
    }

    @Override
    @Transactional
    public EnrollmentResponseDto updateGrade(Long id, String grade) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with ID: " + id));

        enrollment.setGrade(grade != null ? grade.toUpperCase() : null);
        Enrollment updatedEnrollment = enrollmentRepository.save(enrollment);
        return mapToDto(updatedEnrollment);
    }

    @Override
    @Transactional
    public void unenrollStudent(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with ID: " + id));
        enrollmentRepository.delete(enrollment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnrollmentResponseDto> getEnrollmentsByStudent(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Student not found with ID: " + studentId);
        }
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);
        return enrollments.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnrollmentResponseDto> getEnrollmentsByCourse(Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new ResourceNotFoundException("Course not found with ID: " + courseId);
        }
        List<Enrollment> enrollments = enrollmentRepository.findByCourseId(courseId);
        return enrollments.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnrollmentResponseDto> getAllEnrollments() {
        List<Enrollment> enrollments = enrollmentRepository.findAll();
        return enrollments.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EnrollmentResponseDto getEnrollmentById(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with ID: " + id));
        return mapToDto(enrollment);
    }

    // Mapper Methods
    private EnrollmentResponseDto mapToDto(Enrollment entity) {
        DepartmentDto studentDept = null;
        if (entity.getStudent().getDepartment() != null) {
            studentDept = DepartmentDto.builder()
                    .id(entity.getStudent().getDepartment().getId())
                    .name(entity.getStudent().getDepartment().getName())
                    .code(entity.getStudent().getDepartment().getCode())
                    .headOfDepartment(entity.getStudent().getDepartment().getHeadOfDepartment())
                    .build();
        }

        StudentResponseDto studentDto = StudentResponseDto.builder()
                .id(entity.getStudent().getId())
                .firstName(entity.getStudent().getFirstName())
                .lastName(entity.getStudent().getLastName())
                .email(entity.getStudent().getEmail())
                .dateOfBirth(entity.getStudent().getDateOfBirth())
                .enrollmentDate(entity.getStudent().getEnrollmentDate())
                .department(studentDept)
                .build();

        DepartmentDto courseDept = null;
        if (entity.getCourse().getDepartment() != null) {
            courseDept = DepartmentDto.builder()
                    .id(entity.getCourse().getDepartment().getId())
                    .name(entity.getCourse().getDepartment().getName())
                    .code(entity.getCourse().getDepartment().getCode())
                    .headOfDepartment(entity.getCourse().getDepartment().getHeadOfDepartment())
                    .build();
        }

        CourseResponseDto courseDto = CourseResponseDto.builder()
                .id(entity.getCourse().getId())
                .courseCode(entity.getCourse().getCourseCode())
                .title(entity.getCourse().getTitle())
                .description(entity.getCourse().getDescription())
                .credits(entity.getCourse().getCredits())
                .department(courseDept)
                .build();

        return EnrollmentResponseDto.builder()
                .id(entity.getId())
                .student(studentDto)
                .course(courseDto)
                .enrollmentDate(entity.getEnrollmentDate())
                .grade(entity.getGrade())
                .build();
    }
}
