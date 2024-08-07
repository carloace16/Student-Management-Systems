package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {

    // Handles the POST request to add a new student
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the name and email parameters from the request
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        // Create a new Student object with the provided name and email
        Student student = new Student(name, email);

        // Create an instance of StudentDAO to interact with the database
        StudentDAO studentDAO = new StudentDAO();

        try {
            // Attempt to add the new student to the database
            studentDAO.addStudent(student);
        } catch (SQLException e) {
            // If there's a database error, throw a ServletException
            throw new ServletException("Database error", e);
        }

        // Redirect to the viewStudents.jsp page to display the updated list of students
        response.sendRedirect("viewStudents.jsp");
    }
}
