package com.example.students.frontend;

import com.example.students.data.StudentRepository;
import com.example.students.resource.CreateStudent;
import com.example.students.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/students-page")
public class StudentsPageController {

    private final StudentRepository studentRepository;
    private final StudentService studentService;

    public StudentsPageController(StudentRepository studentRepository, StudentService studentService) {
        this.studentRepository = studentRepository;
        this.studentService = studentService;
    }

    @GetMapping
    public String getStudentsPage(String name, Model model){
        model.addAttribute("name", name);
        var students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "index";
    }

    @GetMapping("/add")
    public String returnAddStudentPage(Model model) {
        model.addAttribute("student", new CreateStudent());
        return "addStudent";
    }

    @PostMapping("/add")
    public String addStudentAndRedirectToMainPage(@ModelAttribute CreateStudent createStudent){
        studentService.createStudent(createStudent);
        return "redirect:/students-page";
    }
}
