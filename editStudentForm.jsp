<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.Student" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Student</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f8ff;
            color: #333;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 80%;
            max-width: 600px;
        }
        h1 {
            color: #007acc;
            text-align: center;
        }
        form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        label {
            margin-bottom: 5px;
            text-align: left;
            width: 100%;
        }
        input[type="text"], input[type="email"] {
            padding: 8px;
            width: calc(100% - 16px);
            border: 1px solid #ccc;
            border-radius: 4px;
            margin-bottom: 15px;
        }
        input[type="submit"] {
            padding: 10px;
            background-color: #007acc;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        input[type="submit"]:hover {
            background-color: #005fa3;
        }
        .message {
            color: red;
            text-align: center;
            margin-top: 10px;
        }
        a {
            display: block;
            margin-top: 20px;
            padding: 10px;
            text-decoration: none;
            color: #fff;
            background-color: #007acc;
            border-radius: 5px;
            text-align: center;
            transition: background-color 0.3s ease;
        }
        a:hover {
            background-color: #005fa3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Edit Student</h1>
        <form action="EditStudentServlet" method="post">
            <%
                Student student = (Student) request.getAttribute("student");
                if (student != null) {
            %>
                <input type="hidden" name="id" value="<%= student.getId() %>">
                <label>Name:</label>
                <input type="text" name="name" value="<%= student.getName() %>" required>
                <label>Email:</label>
                <input type="email" name="email" value="<%= student.getEmail() %>" required>
                <input type="submit" value="Update Student">
            <%
                } else {
            %>
                <p class="message">Student not found.</p>
            <%
                }
            %>
        </form>
        <a href="editStudent.jsp">Back to Student List</a>
    </div>
</body>
</html>
