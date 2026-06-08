package com.janu.studentmanagement.service;

import com.janu.studentmanagement.dto.StudentRequestDto;
import com.janu.studentmanagement.dto.StudentResponseDto;

import java.util.List;

public interface StudentService {
    StudentResponseDto createStudent(StudentRequestDto studentRequestDto);
    StudentResponseDto getStudentById(Long id);
    List<StudentResponseDto> getAllStudents();
    StudentResponseDto updateStudent(Long id, StudentRequestDto studentRequestDto);
    void deleteStudent(Long id);
    StudentResponseDto getStudentByEmail(String email);
    List<StudentResponseDto> getStudentsByDepartment(Long departmentId);
}
