package com.iet.result_management.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;      // e.g. Mid Term, End Term
    private String semester;
    private LocalDate examDate;

    public Exam() {}

    public Exam(String name, String semester, LocalDate examDate) {
        this.name = name;
        this.semester = semester;
        this.examDate = examDate;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getSemester() { return semester; }
    public LocalDate getExamDate() { return examDate; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setSemester(String semester) { this.semester = semester; }
    public void setExamDate(LocalDate examDate) { this.examDate = examDate; }
}
