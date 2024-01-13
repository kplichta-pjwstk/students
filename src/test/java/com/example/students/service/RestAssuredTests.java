package com.example.students.service;

import com.example.students.data.Student;
import com.example.students.data.StudentRepository;
import com.example.students.data.StudentUnit;
import com.example.students.resource.CreateStudent;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@AutoConfigureMockMvc
public class RestAssuredTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        mockMvc(mockMvc);
    }

    @AfterEach
    void cleanUp() {
        studentRepository.deleteAll();
    }

    @Test
    void givenCreateStudentWhenPostStudentsThenStudentCreated() {
        var createStudent = new CreateStudent("Karola", StudentUnit.GDANSK);
        given()
           .body(createStudent)
           .contentType(MediaType.APPLICATION_JSON)
        .when()
           .post("/students")
        .then()
           .status(HttpStatus.CREATED);

        var students = studentRepository.findByName(createStudent.getName());
        assertEquals(students.size(), 1);
        assertEquals(students.get(0).getName(), createStudent.getName());
        assertEquals(students.get(0).getUnit(), createStudent.getUnit());
    }

    @Test
    void givenStudentInDbWhenGetByIdThenReturnStudentDto() {
        var student = new Student(UUID.randomUUID(), "Karola", StudentUnit.GDANSK, 14L);
        student = studentRepository.save(student);

        when()
            .get("/students/" + student.getId())
        .then()
            .status(HttpStatus.OK)
            .body("id", equalTo(student.getId().toString()))
            .body("name", equalTo(student.getName()))
            .body("unit", equalTo(student.getUnit().toString()))
            .body("index", comparesEqualTo(student.getIndex().intValue()));
    }

    @Test
    void givenFewStudentsWhenGetByNameThenShouldReturnValidStudents() {
        var student1 = new Student("Karola", StudentUnit.GDANSK, 15L);
        var student2 = new Student("Karola", StudentUnit.WARSZAWA, 25L);
        var student3 = new Student("Jan", StudentUnit.GDANSK, 17L);
        student1 = studentRepository.save(student1);
        studentRepository.saveAll(List.of(student2, student3));

        given()
           .param("name", "Karola")
        .when()
           .get("/students")
        .then()
           .body("$.size()", equalTo(1))
           .body("[0].id", equalTo(student1.getId().toString()))
           .body("[0].name", equalTo(student1.getName()))
           .body("[0].unit", equalTo(student1.getUnit().toString()))
           .body("[0].index", equalTo(student1.getIndex().intValue()));
    }
}
