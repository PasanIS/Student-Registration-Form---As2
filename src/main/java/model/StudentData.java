package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentData {

    private static List<Student> studentList = new ArrayList<>();

    //--- Add New Students to the List
    public static void addStudent(Student student) {
        studentList.add(student);
    }

    //--- Register a student by Email
    public static boolean registerStudent(Student student) {
        for (Student stu : studentList) {
            if (stu.getEmail().equalsIgnoreCase(student.getEmail())) {
                return false; // Email Already Exists
            }
        }
        studentList.add(student);
        return true;
    }

    //--- Login by Email & Password
    public static Student login(String email, String password) throws Exception {

        Connection connection = db.DBConnection.getConnection();

        String sql = "SELECT * FROM students WHERE email = ? AND password = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Student student = new Student(
                    resultSet.getString("student_id"),
                    resultSet.getString("full_name"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getDate("dob").toLocalDate(),
                    resultSet.getString("gender")
            );
            statement.close();
            connection.close();
            return student;
        }
        statement.close();
        connection.close();
        return null;
    }

    //--- Check the Email (already registered or not)
    public static boolean isEmailRegistered(String email) throws Exception {
        Connection connection = db.DBConnection.getConnection();

        String sql = "SELECT * FROM students WHERE email = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);

        ResultSet resultSet = statement.executeQuery();
        boolean exists = resultSet.next();

        statement.close();
        connection.close();
        return exists;
    }

//        for (Student stu : studentList) {
//            if (stu.getEmail().equalsIgnoreCase(email) &&
//                    stu.getPassword().equalsIgnoreCase(password)) {
//                return stu;
//            }
//        }
//        return null; //--- Not Found or Wrong Password
//    }
//

//
//    //--- Get all students
//    public static List<Student> getAllStudents() {
//        return studentList;
//    }
}
