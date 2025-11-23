package com.iet.result_management.entity;

import jakarta.persistence.*;

@Entity
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Student student;

    @ManyToOne(optional = false)
    private Subject subject;

    @ManyToOne(optional = false)
    private Exam exam;

    private Integer marks;   // marks obtained
    private String grade;    // A/B/C/D/F
    private String status;   // PASS / FAIL

    public Result() {
    }

    public Result(Student student,
                  Subject subject,
                  Exam exam,
                  Integer marks,
                  String grade,
                  String status) {
        this.student = student;
        this.subject = subject;
        this.exam = exam;
        this.marks = marks;
        this.grade = grade;
        this.status = status;
    }

    // ===== GETTERS =====

    public Long getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public Subject getSubject() {
        return subject;
    }

    public Exam getExam() {
        return exam;
    }

    public Integer getMarks() {
        return marks;
    }

    public String getGrade() {
        return grade;
    }

    public String getStatus() {
        return status;
    }

    // ===== SETTERS =====

    public void setId(Long id) {
        this.id = id;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
