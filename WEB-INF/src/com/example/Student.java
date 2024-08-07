package com.example;

public class Student {
    private int id;
    private String name;
    private String email;

    // Constructor for creating a new student (without ID)
    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Constructor for creating a student with ID
    public Student(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
