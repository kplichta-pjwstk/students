package com.example.students.service;

import com.example.students.data.Student;
import com.example.students.data.StudentRepository;
import com.example.students.data.StudentUnit;
import com.example.students.exception.ResourceNotFoundException;
import com.example.students.mappery.StudentMapper;
import com.example.students.resource.CreateStudent;
import com.example.students.resource.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public Student createStudent(CreateStudent createStudent) {
        var studentToSave = studentMapper.toEntity(createStudent);
        var index = createIndex(createStudent.getUnit());
        studentToSave.setIndex(index);
        studentRepository.save(studentToSave);
        return studentToSave;
    }

    public StudentDto getStudentById(UUID id) {
        return studentRepository.findById(id)
                .map(studentMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Student " + id + "not found"));
    }

    public void deleteByName(String name) {
        studentRepository.deleteByName(name);
    }

    private Long createIndex(StudentUnit unit) {
        var maxIndex = studentRepository.findMaxIndex().orElse(1L);
        if (StudentUnit.GDANSK.equals(unit)) {
            return 5 * maxIndex;
        } else {
            return 10 * maxIndex;
        }
    }
}
