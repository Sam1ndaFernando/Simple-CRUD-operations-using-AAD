package dao;

import dto.StudentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class StudentDataProcess implements StudentData {
    static String SAVE_STUDENT = "INSERT INTO students (id, name, email, city, level) VALUES (?, ?, ?, ?, ?)";
    static String GET_STUDENT = "SELECT * FROM students WHERE id=?";
    static String DELETE_STUDENT = "DELETE FROM students WHERE id=?";
    static String UPDATE_STUDENT = "UPDATE students SET name=?, email=?, city=?, level=? WHERE id=?";

    @Override
    public StudentDto getStudent(String StudentId, Connection connection) {
        StudentDto studentDto = new StudentDto();

        try{
            var ps = connection.prepareStatement(GET_STUDENT);
            ps.setString(1, StudentId);
            var resultSet = ps.executeQuery();
            while(resultSet.next()){
                studentDto.setId(resultSet.getString("id"));
                studentDto.setName(resultSet.getString("name"));
                studentDto.setEmail(resultSet.getString("email"));
                studentDto.setCity(resultSet.getString("city"));
                studentDto.setLevel(resultSet.getString("level"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return studentDto;
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
