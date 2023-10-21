package com.example.students.service;

import com.example.students.data.Student;
import com.example.students.data.StudentRepository;
import com.example.students.data.StudentUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Student createStudent(Student student) {
        var index = createIndex(student.getUnit());
        var studentToSave = new Student(student.getId(), student.getName(), student.getUnit(), index);
        studentRepository.save(studentToSave);
        return studentToSave;
    }

    public Optional<Student> getStudentById(UUID id) {
        return studentRepository.findById(id);
    }

    public void deleteByName(String name) {
        studentRepository.deleteByName(name);
    }

    private Long createIndex(StudentUnit unit) {
        if(StudentUnit.GDANSK.equals(unit)) {
            return 5 * studentRepository.findMaxIndex();
        } else {
            return 10* studentRepository.findMaxIndex();
        }
    }
}
