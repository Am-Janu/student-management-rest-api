package com.janu.studentmanagement.service;

import com.janu.studentmanagement.dto.CourseRequestDto;
import com.janu.studentmanagement.dto.CourseResponseDto;

import java.util.List;

public interface CourseService {
    CourseResponseDto createCourse(CourseRequestDto courseRequestDto);
    CourseResponseDto getCourseById(Long id);
    List<CourseResponseDto> getAllCourses();
    CourseResponseDto updateCourse(Long id, CourseRequestDto courseRequestDto);
    void deleteCourse(Long id);
    List<CourseResponseDto> getCoursesByDepartment(Long departmentId);
    List<CourseResponseDto> getCoursesByCredits(Integer credits);
}
