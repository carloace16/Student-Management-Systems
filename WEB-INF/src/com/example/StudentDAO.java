package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // Method to get a database connection
    public Connection getConnection() throws SQLException {
        return DatabaseUtil.getConnection();
    }

    // Method to add a new student to the database
    public void addStudent(Student student) throws SQLException {
        String query = "INSERT INTO students (name, email) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            // Set the name and email parameters in the SQL query
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            // Execute the update query
            statement.executeUpdate();
        }
    }

    // Method to retrieve all students from the database
    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            // Iterate through the result set and create Student objects
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                students.add(new Student(id, name, email));
            }
        }
        return students;
    }

    // Method to update an existing student in the database
    public void updateStudent(Student student) throws SQLException {
        String query = "UPDATE students SET name = ?, email = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            // Set the updated name, email, and student ID in the SQL query
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setInt(3, student.getId());
            // Execute the update query
            statement.executeUpdate();
        }
    }

    // Method to delete a student from the database by ID
    public void deleteStudent(int id) throws SQLException {
        String query = "DELETE FROM students WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            // Set the student ID in the SQL query
            statement.setInt(1, id);
            // Execute the delete query
            statement.executeUpdate();
        }
    }

    // Method to retrieve a student from the database by ID
    public Student getStudentById(int id) throws SQLException {
        String query = "SELECT * FROM students WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            // Set the student ID in the SQL query
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                // If a student is found, create and return a Student object
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    return new Student(id, name, email);
                }
            }
        }
        return null; // Return null if no student is found with the given ID
    }

}
