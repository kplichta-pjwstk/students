package com.example.students.service;

import com.example.students.data.Student;
import com.example.students.data.StudentRepository;
import com.example.students.data.StudentUnit;
import com.example.students.mappery.StudentMapper;
import com.example.students.resource.CreateStudent;
import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


//testy zakomentowane - na następnych zajęciach będziemy zajmować się ich poprawą
@Log
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    private StudentRepository studentRepository;
    private StudentMapper studentMapper;
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentRepository = mock(StudentRepository.class);
        studentMapper = spy(StudentMapper.class);
        studentService = new StudentService(studentRepository, studentMapper);
        when(studentRepository.findMaxIndex()).thenReturn(Optional.of(5L));
    }

    @Test
    void givenStudentWithUnitGdanskWhenCreateStudentThenStudentWasSavedWithValidData() {
        var student = new CreateStudent("Karola", StudentUnit.GDANSK);

        var savedStudent = studentService.createStudent(student);

        assertEquals(student.getName(), savedStudent.getName());
        assertEquals(student.getUnit(), savedStudent.getUnit());
        assertEquals(25L, savedStudent.getIndex());
        verify(studentRepository, times(1)).findMaxIndex();
    }

    @Test
    void givenStudentWithUnitWarszawaWhenCreateStudentThenStudentWasSavedWithValidData() {
        var student = new CreateStudent("Karola", StudentUnit.WARSZAWA);
        ArgumentCaptor<Student> captor = ArgumentCaptor.forClass(Student.class);

        var savedStudent = studentService.createStudent(student);

        assertEquals(student.getName(), savedStudent.getName());
        assertEquals(student.getUnit(), savedStudent.getUnit());
        assertEquals(50L, savedStudent.getIndex());
        verify(studentRepository, times(1)).findMaxIndex();
        verify(studentRepository, times(1)).save(captor.capture());
        var studentArg = captor.getValue();
        assertEquals(student.getName(), studentArg.getName());
        assertEquals(student.getUnit(), studentArg.getUnit());
        assertEquals(50L, studentArg.getIndex());
    }

}
