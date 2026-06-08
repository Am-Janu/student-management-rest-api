package com.janu.studentmanagement.service.impl;

import com.janu.studentmanagement.dto.DepartmentDto;
import com.janu.studentmanagement.dto.StudentRequestDto;
import com.janu.studentmanagement.dto.StudentResponseDto;
import com.janu.studentmanagement.entity.Department;
import com.janu.studentmanagement.entity.Student;
import com.janu.studentmanagement.exception.DuplicateResourceException;
import com.janu.studentmanagement.exception.ResourceNotFoundException;
import com.janu.studentmanagement.repository.DepartmentRepository;
import com.janu.studentmanagement.repository.StudentRepository;
import com.janu.studentmanagement.service.StudentService;
import com.janu.studentmanagement.repository.DepartmentRepository;
import com.janu.studentmanagement.repository.StudentRepository;
import com.janu.studentmanagement.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;

    public StudentServiceImpl(StudentRepository studentRepository, DepartmentRepository departmentRepository) {
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional
    public StudentResponseDto createStudent(StudentRequestDto studentRequestDto) {
        if (studentRepository.existsByEmail(studentRequestDto.getEmail())) {
            throw new DuplicateResourceException("Student with email " + studentRequestDto.getEmail() + " already exists.");
        }

        Department department = departmentRepository.findById(studentRequestDto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + studentRequestDto.getDepartmentId()));

        Student student = mapToEntity(studentRequestDto, department);
        Student savedStudent = studentRepository.save(student);
        return mapToDto(savedStudent);
    }

    @Override
    @Transactional(readOnly = true)
    public StudentResponseDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + id));
        return mapToDto(student);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentResponseDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public StudentResponseDto updateStudent(Long id, StudentRequestDto studentRequestDto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + id));

        if (!student.getEmail().equals(studentRequestDto.getEmail()) &&
                studentRepository.existsByEmail(studentRequestDto.getEmail())) {
            throw new DuplicateResourceException("Student with email " + studentRequestDto.getEmail() + " already exists.");
        }

        Department department = departmentRepository.findById(studentRequestDto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + studentRequestDto.getDepartmentId()));

        student.setFirstName(studentRequestDto.getFirstName());
        student.setLastName(studentRequestDto.getLastName());
        student.setEmail(studentRequestDto.getEmail());
        student.setDateOfBirth(studentRequestDto.getDateOfBirth());
        student.setEnrollmentDate(studentRequestDto.getEnrollmentDate());
        student.setDepartment(department);

        Student updatedStudent = studentRepository.save(student);
        return mapToDto(updatedStudent);
    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + id));
        studentRepository.delete(student);
    }

    @Override
    @Transactional(readOnly = true)
    public StudentResponseDto getStudentByEmail(String email) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with email: " + email));
        return mapToDto(student);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentResponseDto> getStudentsByDepartment(Long departmentId) {
        if (!departmentRepository.existsById(departmentId)) {
            throw new ResourceNotFoundException("Department not found with ID: " + departmentId);
        }
        List<Student> students = studentRepository.findByDepartmentId(departmentId);
        return students.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    // Mapper Methods
    private Student mapToEntity(StudentRequestDto dto, Department department) {
        return Student.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .dateOfBirth(dto.getDateOfBirth())
                .enrollmentDate(dto.getEnrollmentDate())
                .department(department)
                .build();
    }

    private StudentResponseDto mapToDto(Student entity) {
        DepartmentDto departmentDto = null;
        if (entity.getDepartment() != null) {
            departmentDto = DepartmentDto.builder()
                    .id(entity.getDepartment().getId())
                    .name(entity.getDepartment().getName())
                    .code(entity.getDepartment().getCode())
                    .headOfDepartment(entity.getDepartment().getHeadOfDepartment())
                    .build();
        }

        return StudentResponseDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .dateOfBirth(entity.getDateOfBirth())
                .enrollmentDate(entity.getEnrollmentDate())
                .department(departmentDto)
                .build();
    }
}
