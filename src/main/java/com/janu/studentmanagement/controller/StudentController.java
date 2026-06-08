package com.janu.studentmanagement.controller;

import com.janu.studentmanagement.dto.StudentRequestDto;
import com.janu.studentmanagement.dto.StudentResponseDto;
import com.janu.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import com.janu.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(@Valid @RequestBody StudentRequestDto studentRequestDto) {
        StudentResponseDto createdStudent = studentService.createStudent(studentRequestDto);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable Long id) {
        StudentResponseDto studentResponseDto = studentService.getStudentById(id);
        return ResponseEntity.ok(studentResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> getAllStudents() {
        List<StudentResponseDto> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentRequestDto studentRequestDto) {
        StudentResponseDto updatedStudent = studentService.updateStudent(id, studentRequestDto);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully with ID: " + id);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<StudentResponseDto> getStudentByEmail(@PathVariable String email) {
        StudentResponseDto studentResponseDto = studentService.getStudentByEmail(email);
        return ResponseEntity.ok(studentResponseDto);
    }

    @GetMapping("/department/{deptId}")
    public ResponseEntity<List<StudentResponseDto>> getStudentsByDepartment(@PathVariable Long deptId) {
        List<StudentResponseDto> students = studentService.getStudentsByDepartment(deptId);
        return ResponseEntity.ok(students);
    }
}
