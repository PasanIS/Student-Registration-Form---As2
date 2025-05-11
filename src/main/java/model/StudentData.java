package model;

import java.util.ArrayList;
import java.util.List;

public class StudentData {

    private static List<Student> studentList = new ArrayList<>();
    private static int lastStudentId = 0;

    //--- Add new Student to the List
    public static void addStudent(Student student) {
        studentList.add(student);
    }

    //--- Register Students By Email
    public static boolean registerStudent(Student student) {
        for (Student stu : studentList) {
            if (stu.getEmail().equalsIgnoreCase(student.getEmail())) {
                return false; //--- Email Already Exists
            }
        }
        studentList.add(student);
        lastStudentId++;
        return true;
    }

    //--- Generate next Student ID
    public static String generateStudentId(){
        lastStudentId++;
        return String.format("STU-%04d", lastStudentId); //--- ID : "STU-0001"
    }

    //--- Login Student
    public static Student login(String email, String password) {
        for (Student stu : studentList) {
            if (stu.getEmail().equalsIgnoreCase(email) && stu.getPassword().equals(password)) {
                return stu;
            }
        }
        return null;
    }

    //--- Check the Email(Already registered or not)
    public static boolean isEmailRegistered(String email) {
        for (Student stu : studentList) {
            if (stu.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }
}
