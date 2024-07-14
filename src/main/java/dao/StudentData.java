package dao;

import dto.StudentDto;

import java.sql.Connection;

public sealed interface StudentData permits StudentDataProcess {
    StudentDto getStudent(String StudentId, Connection connection);
    boolean saveStudent(StudentDto student, Connection connection);
    boolean deleteStudent(String StudentId, Connection connection);
    boolean updateStudent(String studentId,StudentDto student, Connection connection);
}
