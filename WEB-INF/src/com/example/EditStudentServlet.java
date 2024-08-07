package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/EditStudentServlet")
public class EditStudentServlet extends HttpServlet {

    // Handles the GET request to retrieve a student's details for editing
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("id")); // Get the student ID from the request
        StudentDAO studentDAO = new StudentDAO();
        Student student = null;

        try {
            // Retrieve the student details from the database using the ID
            student = studentDAO.getStudentById(studentId);
        } catch (SQLException e) {
            // If there's a database error, throw a ServletException
            throw new ServletException("Database error", e);
        }

        if (student != null) {
            // If student is found, set it as a request attribute and forward to editStudent.jsp
            request.setAttribute("student", student);
            request.getRequestDispatcher("editStudentForm.jsp").forward(request, response);
        } else {
            // If student is not found, print an error message
            response.getWriter().println("Student not found. ID: " + studentId);
        }
    }

    // Handles the POST request to update a student's details
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        // Create a new Student object with the updated details
        Student student = new Student(id, name, email);
        StudentDAO studentDAO = new StudentDAO();

        try {
            // Attempt to update the student details in the database
            studentDAO.updateStudent(student);
        } catch (SQLException e) {
            // If there's a database error, throw a ServletException
            throw new ServletException("Database error", e);
        }

        // Redirect to the ViewStudentsServlet to display the updated list of students
        response.sendRedirect("ViewStudentsServlet");
    }
}
