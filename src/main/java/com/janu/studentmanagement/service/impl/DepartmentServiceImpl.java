package com.janu.studentmanagement.service.impl;

import com.janu.studentmanagement.dto.DepartmentDto;
import com.janu.studentmanagement.entity.Department;
import com.janu.studentmanagement.exception.DuplicateResourceException;
import com.janu.studentmanagement.exception.ResourceNotFoundException;
import com.janu.studentmanagement.repository.DepartmentRepository;
import com.janu.studentmanagement.service.DepartmentService;
import com.janu.studentmanagement.repository.DepartmentRepository;
import com.janu.studentmanagement.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        if (departmentRepository.existsByCode(departmentDto.getCode())) {
            throw new DuplicateResourceException("Department with code " + departmentDto.getCode() + " already exists.");
        }

        Department department = mapToEntity(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        return mapToDto(savedDepartment);
    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentDto getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + id));
        return mapToDto(department);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DepartmentDto updateDepartment(Long id, DepartmentDto departmentDto) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + id));

        // If code changes, ensure new code is unique
        if (!department.getCode().equals(departmentDto.getCode()) &&
                departmentRepository.existsByCode(departmentDto.getCode())) {
            throw new DuplicateResourceException("Department with code " + departmentDto.getCode() + " already exists.");
        }

        department.setName(departmentDto.getName());
        department.setCode(departmentDto.getCode());
        department.setHeadOfDepartment(departmentDto.getHeadOfDepartment());

        Department updatedDepartment = departmentRepository.save(department);
        return mapToDto(updatedDepartment);
    }

    @Override
    @Transactional
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + id));
        departmentRepository.delete(department);
    }

    // Mapper Methods
    private Department mapToEntity(DepartmentDto dto) {
        return Department.builder()
                .id(dto.getId())
                .name(dto.getName())
                .code(dto.getCode())
                .headOfDepartment(dto.getHeadOfDepartment())
                .build();
    }

    private DepartmentDto mapToDto(Department entity) {
        return DepartmentDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .code(entity.getCode())
                .headOfDepartment(entity.getHeadOfDepartment())
                .build();
    }
}
