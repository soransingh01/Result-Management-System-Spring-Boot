package com.iet.result_management.service;

import com.iet.result_management.entity.Student;
import com.iet.result_management.entity.Subject;
import com.iet.result_management.entity.Exam;
import com.iet.result_management.entity.Result;
import com.iet.result_management.repository.StudentRepository;
import com.iet.result_management.repository.SubjectRepository;
import com.iet.result_management.repository.ExamRepository;
import com.iet.result_management.repository.ResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final ExamRepository examRepository;
    private final ResultRepository resultRepository;

    public ResultService(StudentRepository studentRepository,
                         SubjectRepository subjectRepository,
                         ExamRepository examRepository,
                         ResultRepository resultRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.examRepository = examRepository;
        this.resultRepository = resultRepository;
    }

    // ========== STUDENTS ==========

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // ========== SUBJECTS ==========

     

    public Subject addSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    // ========== EXAMS ==========

    public Exam addExam(Exam exam) {
        return examRepository.save(exam);
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    // ========== RESULTS / MARKS ==========

    /**
     * Manage Marks page se call hoga
     */
    public Result addResult(Long studentId,
                            Long subjectId,
                            Long examId,
                            Integer marksObtained) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + subjectId));

        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + examId));

        int maxMarks = subject.getMaxMarks() != null ? subject.getMaxMarks() : 100;

        String grade = calculateGrade(marksObtained, maxMarks);
        String status = marksObtained >= 40 ? "PASS" : "FAIL";

        Result result = new Result();
        result.setStudent(student);
        result.setSubject(subject);
        result.setExam(exam);
        result.setMarks(marksObtained);
        result.setGrade(grade);
        result.setStatus(status);

        return resultRepository.save(result);
    }

    public void updateMarks(Long resultId, Integer newMarks) {
    Result result = resultRepository.findById(resultId)
            .orElseThrow(() -> new RuntimeException("Result not found"));

    int maxMarks = result.getSubject().getMaxMarks();
    String grade = calculateGrade(newMarks, maxMarks);
    String status = newMarks >= 40 ? "PASS" : "FAIL";

    result.setMarks(newMarks);
    result.setGrade(grade);
    result.setStatus(status);

    resultRepository.save(result);
}


    public List<Result> getResultsForStudent(Long studentId) {
        return studentRepository.findById(studentId)
                .map(resultRepository::findByStudent)
                .orElse(List.of());
    }

    // ========== HELPER ==========

    private String calculateGrade(int marks, int maxMarks) {
        double percentage = (marks * 100.0) / maxMarks;

        if (percentage >= 90) return "A";
        else if (percentage >= 75) return "B";
        else if (percentage >= 60) return "C";
        else if (percentage >= 50) return "D";
        else if (percentage >= 40) return "E";
        else return "F";
    }
}
