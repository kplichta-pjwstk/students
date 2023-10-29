package com.example.students.service;

import com.example.students.data.StudentRepository;
import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


//testy zakomentowane - na następnych zajęciach będziemy zajmować się ich poprawą
@Log
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
//        when(studentRepository.findMaxIndex()).thenReturn(5L);
    }
//
//    @Test
//    void givenStudentWithUnitGdanskWhenCreateStudentThenStudentWasSavedWithValidData() {
////        var student = new Student(UUID.randomUUID(), "Karola", StudentUnit.GDANSK, null);
//
//        var savedStudent = studentService.createStudent(student);
//
//        assertEquals(student.id(), savedStudent.id());
//        assertEquals(student.name(), savedStudent.name());
//        assertEquals(student.unit(), savedStudent.unit());
//        assertEquals(25L, savedStudent.index());
//        verify(studentRepository, times(1)).findMaxIndex();
//    }
//
//    @Test
//    void givenStudentWithUnitWarszawaWhenCreateStudentThenStudentWasSavedWithValidData() {
////        var student = new Student(UUID.randomUUID(), "Karola", StudentUnit.WARSZAWA, null);
//        ArgumentCaptor<Student> captor = ArgumentCaptor.forClass(Student.class);
//
//        var savedStudent = studentService.createStudent(student);
//
//        assertEquals(student.id(), savedStudent.id());
//        assertEquals(student.name(), savedStudent.name());
//        assertEquals(student.unit(), savedStudent.unit());
//        assertEquals(50L, savedStudent.index());
//        verify(studentRepository, times(1)).findMaxIndex();
//        verify(studentRepository, times(1)).createStudent(captor.capture());
//        var studentArg = captor.getValue();
//        assertEquals(student.id(), studentArg.id());
//        assertEquals(student.name(), studentArg.name());
//        assertEquals(student.unit(), studentArg.unit());
//        assertEquals(50L, studentArg.index());
//    }

}
