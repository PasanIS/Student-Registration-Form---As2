package model;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class StudentDAO {
    public static void saveStudent(Student student) throws Exception{
        Connection connection = DBConnection.getConnection();

        String sql = "INSERT INTO students (student_id, full_name, email, password, dob, gender) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, student.getStudentId());
        statement.setString(2, student.getFullName());
        statement.setString(3, student.getEmail());
        statement.setString(4, student.getPassword());
        statement.setDate(5, java.sql.Date.valueOf(student.getDob()));
        statement.setString(6, student.getGender());

        statement.executeUpdate();
        statement.close();
        connection.close();
    }
}
