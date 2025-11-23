package com.iet.result_management.controller;

import com.iet.result_management.entity.Exam;
import com.iet.result_management.service.ResultService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/exams")
public class ExamController {

    private final ResultService resultService;

    public ExamController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping
    public String listExams(Model model) {
        model.addAttribute("exams", resultService.getAllExams());
        model.addAttribute("exam", new Exam());
        return "exams";
    }

    @PostMapping
    public String addExam(@ModelAttribute("exam") Exam exam) {
        resultService.addExam(exam);
        return "redirect:/exams";
    }
}
