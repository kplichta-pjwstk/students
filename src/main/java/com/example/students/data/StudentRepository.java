package com.example.students.data;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class StudentRepository {

    private final List<Student> students = new ArrayList<>();

    public void createStudent(Student student) {
        students.add(student);
    }

    public Optional<Student> getStudentById(UUID id) {
        return students.stream()
                .filter(it -> it.id().equals(id))
                .findFirst();
    }

    public void deleteByName(String name) {
        var studentsToRemove = students.stream()
                .filter(it -> it.name().equals(name))
                .toList();
        students.removeAll(studentsToRemove);
    }
}
