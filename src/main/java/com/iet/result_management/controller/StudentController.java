package com.iet.result_management.controller;

import com.iet.result_management.entity.Student;
import com.iet.result_management.service.ResultService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final ResultService resultService;

    public StudentController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", resultService.getAllStudents());
        model.addAttribute("student", new Student());
        return "students";
    }

    @PostMapping
    public String addStudent(@ModelAttribute("student") Student student) {
        resultService.addStudent(student);
        return "redirect:/students";
    }
}
