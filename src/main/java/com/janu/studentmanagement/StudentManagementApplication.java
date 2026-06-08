package com.janu.studentmanagement;

import com.janu.studentmanagement.entity.Course;
import com.janu.studentmanagement.entity.Department;
import com.janu.studentmanagement.entity.Enrollment;
import com.janu.studentmanagement.entity.Student;
import com.janu.studentmanagement.repository.CourseRepository;
import com.janu.studentmanagement.repository.DepartmentRepository;
import com.janu.studentmanagement.repository.EnrollmentRepository;
import com.janu.studentmanagement.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class StudentManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentManagementApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(
            DepartmentRepository departmentRepository,
            StudentRepository studentRepository,
            CourseRepository courseRepository,
            EnrollmentRepository enrollmentRepository) {
        return args -> {
            // Check if database is empty before seeding
            if (departmentRepository.count() == 0) {
                // Seed Departments
                Department cs = Department.builder()
                        .name("Computer Science and Engineering")
                        .code("CSE")
                        .headOfDepartment("Dr. Alan Turing")
                        .build();
                Department ee = Department.builder()
                        .name("Electrical Engineering")
                        .code("EE")
                        .headOfDepartment("Dr. Nikola Tesla")
                        .build();
                Department math = Department.builder()
                        .name("Mathematics")
                        .code("MATH")
                        .headOfDepartment("Dr. Srinivasa Ramanujan")
                        .build();

                cs = departmentRepository.save(cs);
                ee = departmentRepository.save(ee);
                math = departmentRepository.save(math);

                // Seed Students
                Student s1 = Student.builder()
                        .firstName("Janakiraman")
                        .lastName("N")
                        .email("janakiraman.dev@example.com")
                        .dateOfBirth(LocalDate.of(2004, 2, 2))
                        .enrollmentDate(LocalDate.of(2024, 6, 1))
                        .department(cs)
                        .build();
                Student s2 = Student.builder()
                        .firstName("Alice")
                        .lastName("Smith")
                        .email("alice.smith@example.com")
                        .dateOfBirth(LocalDate.of(2003, 5, 12))
                        .enrollmentDate(LocalDate.of(2024, 6, 1))
                        .department(cs)
                        .build();
                Student s3 = Student.builder()
                        .firstName("Bob")
                        .lastName("Johnson")
                        .email("bob.johnson@example.com")
                        .dateOfBirth(LocalDate.of(2002, 9, 20))
                        .enrollmentDate(LocalDate.of(2023, 6, 1))
                        .department(ee)
                        .build();

                s1 = studentRepository.save(s1);
                s2 = studentRepository.save(s2);
                s3 = studentRepository.save(s3);

                // Seed Courses
                Course c1 = Course.builder()
                        .courseCode("CSE-101")
                        .title("Introduction to Programming")
                        .description("Fundamentals of structured programming using Java.")
                        .credits(4)
                        .department(cs)
                        .build();
                Course c2 = Course.builder()
                        .courseCode("CSE-201")
                        .title("Data Structures and Algorithms")
                        .description("Study of fundamental data structures and algorithm analysis.")
                        .credits(4)
                        .department(cs)
                        .build();
                Course c3 = Course.builder()
                        .courseCode("EE-102")
                        .title("Basic Circuit Theory")
                        .description("Analysis of electrical circuits and components.")
                        .credits(3)
                        .department(ee)
                        .build();

                c1 = courseRepository.save(c1);
                c2 = courseRepository.save(c2);
                c3 = courseRepository.save(c3);

                // Seed Enrollments
                Enrollment e1 = Enrollment.builder()
                        .student(s1)
                        .course(c1)
                        .enrollmentDate(LocalDate.of(2024, 6, 10))
                        .grade("A+")
                        .build();
                Enrollment e2 = Enrollment.builder()
                        .student(s1)
                        .course(c2)
                        .enrollmentDate(LocalDate.of(2024, 6, 10))
                        .grade("A")
                        .build();
                Enrollment e3 = Enrollment.builder()
                        .student(s2)
                        .course(c1)
                        .enrollmentDate(LocalDate.of(2024, 6, 10))
                        .grade("B")
                        .build();
                Enrollment e4 = Enrollment.builder()
                        .student(s3)
                        .course(c3)
                        .enrollmentDate(LocalDate.of(2023, 6, 15))
                        .grade("A")
                        .build();

                enrollmentRepository.save(e1);
                enrollmentRepository.save(e2);
                enrollmentRepository.save(e3);
                enrollmentRepository.save(e4);

                System.out.println(">>> Mock database initialization complete: seeded departments, students, courses, and enrollments.");
            }
        };
    }
}
