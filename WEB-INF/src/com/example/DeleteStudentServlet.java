package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DeleteStudentServlet")
public class DeleteStudentServlet extends HttpServlet {

    // Handles GET requests to delete a student
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the student ID from the request parameter
        int id = Integer.parseInt(request.getParameter("id"));

        // Create an instance of StudentDAO to interact with the database
        StudentDAO studentDAO = new StudentDAO();

        try {
            // Call the method to delete the student with the given ID
            studentDAO.deleteStudent(id);
        } catch (SQLException e) {
            // Handle any SQL exceptions by throwing a ServletException
            throw new ServletException("Database error", e);
        }

        // Redirect the user to the page displaying all students after deletion
        response.sendRedirect("viewStudents.jsp");
    }
}
