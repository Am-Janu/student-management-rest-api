# student-management-rest-api
Spring Boot REST API for student management with CRUD operations, Hibernate ORM, MySQL integration, and layered architecture.


# Student Management REST API

A Spring Boot and Maven-based RESTful API designed to manage academic entities: Students, Courses, Departments, and Enrollments.

This project implements a clean **Controller-Service-Repository** pattern with robust exception handling and validation.

## Tech Stack
- **Framework**: Spring Boot 3.2.5
- **Build Tool**: Maven
- **Database**: H2 (In-Memory for Dev) / MySQL (Production Profile)
- **ORM**: Hibernate / Spring Data JPA
- **Language**: Java 17

## Features
- **Department Management**: Add, view, edit, or delete departments.
- **Student Management**: Full CRUD operations with email uniqueness constraints.
- **Course Catalog**: Organize courses by department and credit weights.
- **Academic Enrollment**: Enroll students in courses, update course grades, and track academic records.
- **Global Error Handling**: Custom API exceptions mapped cleanly to structured JSON responses.

---

## Getting Started

### Prerequisites
- JDK 17 or higher
- Maven 3.6+

### Build the Project
Compile and build the project package using Maven:
```bash
mvn clean package
```

### Run the Application
Start the application using Maven:
```bash
mvn spring-boot:run
```
Once started, the server runs locally on: **`http://localhost:8080`**

### Accessing the Database (H2 Console)
By default, the application runs on an in-memory database.
1. Visit: `http://localhost:8080/h2-console`
2. **JDBC URL**: `jdbc:h2:mem:studentdb`
3. **Username**: `sa`
4. **Password**: *(Leave blank)*

---

## API Endpoints

### 1. Departments
- **Create Department**: `POST /api/departments`
- **Get All Departments**: `GET /api/departments`
- **Get Department by ID**: `GET /api/departments/{id}`
- **Update Department**: `PUT /api/departments/{id}`
- **Delete Department**: `DELETE /api/departments/{id}`

### 2. Students
- **Create Student**: `POST /api/students`
- **Get All Students**: `GET /api/students`
- **Get Student by ID**: `GET /api/students/{id}`
- **Update Student**: `PUT /api/students/{id}`
- **Delete Student**: `DELETE /api/students/{id}`
- **Find Student by Email**: `GET /api/students/email/{email}`
- **Find Students by Department**: `GET /api/students/department/{deptId}`

### 3. Courses
- **Create Course**: `POST /api/courses`
- **Get All Courses**: `GET /api/courses`
- **Get Course by ID**: `GET /api/courses/{id}`
- **Update Course**: `PUT /api/courses/{id}`
- **Delete Course**: `DELETE /api/courses/{id}`
- **Find Courses by Department**: `GET /api/courses/department/{deptId}`
- **Find Courses by Credits**: `GET /api/courses/credits/{credits}`

### 4. Enrollments
- **Enroll Student**: `POST /api/enrollments`
- **Get All Enrollments**: `GET /api/enrollments`
- **Get Enrollment by ID**: `GET /api/enrollments/{id}`
- **Update Enrollment Grade**: `PUT /api/enrollments/{id}/grade?grade={grade}`
- **Drop Course (Unenroll)**: `DELETE /api/enrollments/{id}`
- **Get Enrollments by Student**: `GET /api/enrollments/student/{studentId}`
- **Get Enrollments by Course**: `GET /api/enrollments/course/{courseId}`

---

## Example Curl Commands

### 1. Create a Department
```bash
curl -X POST http://localhost:8080/api/departments \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Physics Department",
    "code": "PHYS",
    "headOfDepartment": "Dr. Richard Feynman"
  }'
```

### 2. Create a Student
```bash
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@university.edu",
    "dateOfBirth": "2002-04-15",
    "enrollmentDate": "2024-09-01",
    "departmentId": 1
  }'
```

### 3. Enroll a Student in a Course
```bash
curl -X POST http://localhost:8080/api/enrollments \
  -H "Content-Type: application/json" \
  -d '{
    "studentId": 1,
    "courseId": 2,
    "enrollmentDate": "2024-09-05"
  }'
```

### 4. Assign a Grade to Enrollment
```bash
curl -X PUT "http://localhost:8080/api/enrollments/1/grade?grade=A"
```
