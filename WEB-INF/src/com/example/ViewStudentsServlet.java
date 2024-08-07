package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ViewStudentsServlet")
public class ViewStudentsServlet extends HttpServlet {

    // Handles the GET request to retrieve and display the list of all students
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StudentDAO studentDAO = new StudentDAO();
        List<Student> students = null;

        try {
            // Retrieve the list of all students from the database
            students = studentDAO.getAllStudents();
        } catch (SQLException e) {
            // If there's a database error, throw a ServletException
            throw new ServletException("Database error", e);
        }

        // Set the list of students as a request attribute and forward to viewStudents.jsp
        request.setAttribute("students", students);
        request.getRequestDispatcher("viewStudents.jsp").forward(request, response);
    }
}
