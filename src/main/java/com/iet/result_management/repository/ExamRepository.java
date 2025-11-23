package com.iet.result_management.repository;

import com.iet.result_management.entity.Exam;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExamRepository extends JpaRepository<Exam, Long> {

     @Query("select distinct e.semester from Exam e")
    List<String> findDistinctSemesters();
}
