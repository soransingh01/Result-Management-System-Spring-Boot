package com.iet.result_management.entity;

import jakarta.persistence.*;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;
    private Integer maxMarks;

    public Subject() {}

    public Subject(String code, String name, Integer maxMarks) {
        this.code = code;
        this.name = name;
        this.maxMarks = maxMarks;
    }

    public Long getId() { return id; }
    public String getCode() { return code; }
    public String getName() { return name; }
    public Integer getMaxMarks() { return maxMarks; }

    public void setId(Long id) { this.id = id; }
    public void setCode(String code) { this.code = code; }
    public void setName(String name) { this.name = name; }
    public void setMaxMarks(Integer maxMarks) { this.maxMarks = maxMarks; }
}
