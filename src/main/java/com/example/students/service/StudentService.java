package com.example.students.service;

import com.example.students.data.Student;
import com.example.students.data.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public void createStudent(Student student) {
        studentRepository.createStudent(student);
    }

    public Optional<Student> getStudentById(UUID id) {
        return studentRepository.getStudentById(id);
    }

    public void deleteByName(String name) {
        studentRepository.deleteByName(name);
    }
}
