package com.janu.studentmanagement.service.impl;

import com.janu.studentmanagement.dto.CourseRequestDto;
import com.janu.studentmanagement.dto.CourseResponseDto;
import com.janu.studentmanagement.dto.DepartmentDto;
import com.janu.studentmanagement.entity.Course;
import com.janu.studentmanagement.entity.Department;
import com.janu.studentmanagement.exception.DuplicateResourceException;
import com.janu.studentmanagement.exception.ResourceNotFoundException;
import com.janu.studentmanagement.repository.CourseRepository;
import com.janu.studentmanagement.repository.DepartmentRepository;
import com.janu.studentmanagement.service.CourseService;
import com.janu.studentmanagement.repository.CourseRepository;
import com.janu.studentmanagement.repository.DepartmentRepository;
import com.janu.studentmanagement.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;

    public CourseServiceImpl(CourseRepository courseRepository, DepartmentRepository departmentRepository) {
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional
    public CourseResponseDto createCourse(CourseRequestDto courseRequestDto) {
        if (courseRepository.existsByCourseCode(courseRequestDto.getCourseCode())) {
            throw new DuplicateResourceException("Course with code " + courseRequestDto.getCourseCode() + " already exists.");
        }

        Department department = departmentRepository.findById(courseRequestDto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + courseRequestDto.getDepartmentId()));

        Course course = mapToEntity(courseRequestDto, department);
        Course savedCourse = courseRepository.save(course);
        return mapToDto(savedCourse);
    }

    @Override
    @Transactional(readOnly = true)
    public CourseResponseDto getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + id));
        return mapToDto(course);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CourseResponseDto updateCourse(Long id, CourseRequestDto courseRequestDto) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + id));

        if (!course.getCourseCode().equals(courseRequestDto.getCourseCode()) &&
                courseRepository.existsByCourseCode(courseRequestDto.getCourseCode())) {
            throw new DuplicateResourceException("Course with code " + courseRequestDto.getCourseCode() + " already exists.");
        }

        Department department = departmentRepository.findById(courseRequestDto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + courseRequestDto.getDepartmentId()));

        course.setCourseCode(courseRequestDto.getCourseCode());
        course.setTitle(courseRequestDto.getTitle());
        course.setDescription(courseRequestDto.getDescription());
        course.setCredits(courseRequestDto.getCredits());
        course.setDepartment(department);

        Course updatedCourse = courseRepository.save(course);
        return mapToDto(updatedCourse);
    }

    @Override
    @Transactional
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + id));
        courseRepository.delete(course);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponseDto> getCoursesByDepartment(Long departmentId) {
        if (!departmentRepository.existsById(departmentId)) {
            throw new ResourceNotFoundException("Department not found with ID: " + departmentId);
        }
        List<Course> courses = courseRepository.findByDepartmentId(departmentId);
        return courses.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponseDto> getCoursesByCredits(Integer credits) {
        List<Course> courses = courseRepository.findByCredits(credits);
        return courses.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    // Mapper Methods
    private Course mapToEntity(CourseRequestDto dto, Department department) {
        return Course.builder()
                .courseCode(dto.getCourseCode())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .credits(dto.getCredits())
                .department(department)
                .build();
    }

    private CourseResponseDto mapToDto(Course entity) {
        DepartmentDto departmentDto = null;
        if (entity.getDepartment() != null) {
            departmentDto = DepartmentDto.builder()
                    .id(entity.getDepartment().getId())
                    .name(entity.getDepartment().getName())
                    .code(entity.getDepartment().getCode())
                    .headOfDepartment(entity.getDepartment().getHeadOfDepartment())
                    .build();
        }

        return CourseResponseDto.builder()
                .id(entity.getId())
                .courseCode(entity.getCourseCode())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .credits(entity.getCredits())
                .department(departmentDto)
                .build();
    }
}
