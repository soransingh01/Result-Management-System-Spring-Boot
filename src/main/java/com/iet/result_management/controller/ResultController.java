package com.iet.result_management.controller;

import com.iet.result_management.entity.Result;
import com.iet.result_management.entity.Student;
import com.iet.result_management.service.ResultService;
import com.iet.result_management.repository.ResultRepository;
import com.iet.result_management.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
public class ResultController {

    private final ResultService resultService;
    private final StudentRepository studentRepository;
    private final ResultRepository resultRepository;

    // 🔹 Constructor injection – Spring in 3 beans ko inject karega
    public ResultController(ResultService resultService,
                            StudentRepository studentRepository,
                            ResultRepository resultRepository) {
        this.resultService = resultService;
        this.studentRepository = studentRepository;
        this.resultRepository = resultRepository;
    }

    // ================== MANAGE MARKS (/results) ==================

    @GetMapping("/results")
public String manageMarksPage(Model model,
                              @RequestParam(value = "success", required = false) String success) {
    
    model.addAttribute("students", resultService.getAllStudents());
    model.addAttribute("subjects", resultService.getAllSubjects());
    model.addAttribute("exams", resultService.getAllExams());
    model.addAttribute("resultsList", resultRepository.findAll());

    if (success != null) {
        model.addAttribute("success", success);
    }

    return "results";
}

@PostMapping("/results")
public String saveMarks(@RequestParam Long studentId,
                        @RequestParam Long subjectId,
                        @RequestParam Long examId,
                        @RequestParam Integer marksObtained) {

    resultService.addResult(studentId, subjectId, examId, marksObtained);
    return "redirect:/results?success=Marks added successfully!";
}


    // Show Edit Form
@GetMapping("/results/edit/{id}")
public String editMarks(@PathVariable Long id, Model model) {
    Result result = resultRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Result not found"));
    model.addAttribute("result", result);
    return "edit-result";
}

// Update Marks
@PostMapping("/results/edit/{id}")
public String updateMarks(@PathVariable Long id,
                          @RequestParam Integer marks,
                          Model model) {

    resultService.updateMarks(id, marks);
    model.addAttribute("success", "Marks updated successfully!");

    return "redirect:/results";
}

// For showing existing results in Manage Marks
@ModelAttribute("resultsList")
public List<Result> getResultsList() {
    return resultRepository.findAll();
}


    // ================== VIEW RESULT (ROLL NO) ==================

    @GetMapping("/student-result")
    public String studentResultPage(Model model) {
        model.addAttribute("student", null);
        model.addAttribute("results", null);
        return "student-result";   // templates/student-result.html
    }

    @PostMapping("/student-result")
    public String showStudentResult(@RequestParam String rollNumber, Model model) {

        // ❌ StudentRepository.findByRollNumber(...)  (galat)
        // ✅ object ka use karo: studentRepository
        Student student = studentRepository.findByRollNumber(rollNumber);
        model.addAttribute("student", student);

        List<Result> results;
        if (student != null) {
            // yahan bhi object use karna hai
            results = resultRepository.findByStudent(student);
        } else {
            results = Collections.emptyList();
        }

        model.addAttribute("results", results);
        return "student-result";
    }
}
