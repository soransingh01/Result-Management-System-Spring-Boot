package com.iet.result_management.entity;

import jakarta.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String rollNumber;
    private String branch;
    private String semester;

    public Student() {}

    public Student(String name, String rollNumber, String branch, String semester) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.branch = branch;
        this.semester = semester;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getRollNumber() { return rollNumber; }
    public String getBranch() { return branch; }
    public String getSemester() { return semester; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }
    public void setBranch(String branch) { this.branch = branch; }
    public void setSemester(String semester) { this.semester = semester; }

    public static Student findByRollNumber(String rollNumber2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByRollNumber'");
    }
}
