package dao;

import dto.StudentDto;

import java.sql.Connection;

public final class StudentDataProcess implements StudentData {
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
