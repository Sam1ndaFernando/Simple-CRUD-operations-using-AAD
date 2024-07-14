package dao;

import dto.StudentDto;

import java.sql.Connection;

public final class StudentDataProcess implements StudentData {
    static String SAVE_STUDENT = "INSERT INTO students (id, name, email, city, level) VALUES (?, ?, ?, ?, ?)";
    static String GET_STUDENT = "SELECT * FROM students WHERE id=?";
    static String DELETE_STUDENT = "DELETE FROM students WHERE id=?";
    static String UPDATE_STUDENT = "UPDATE students SET name=?, email=?, city=?, level=? WHERE id=?";

    @Override
    public StudentDto getStudent(String StudentId, Connection connection) {
        return null;
    }

    @Override
    public boolean saveStudent(StudentDto student, Connection connection) {
        return false;
    }

    @Override
    public boolean deleteStudent(String StudentId, Connection connection) {
        return false;
    }

    @Override
    public boolean updateStudent(String studentId, StudentDto student, Connection connection) {
        return false;
    }
}
