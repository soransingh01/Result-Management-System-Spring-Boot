package com.iet.result_management.controller;

import com.iet.result_management.entity.Subject;
import com.iet.result_management.service.ResultService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/subjects")
public class SubjectController {

    private final ResultService resultService;

    public SubjectController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping
    public String listSubjects(Model model) {
        model.addAttribute("subjects", resultService.getAllSubjects());
        model.addAttribute("subject", new Subject());
        return "subjects";
    }

    @PostMapping
    public String addSubject(@ModelAttribute("subject") Subject subject) {
        resultService.addSubject(subject);
        return "redirect:/subjects";
    }
}
