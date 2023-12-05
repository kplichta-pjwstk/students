package com.example.students;

import com.example.students.data.Student;
import com.example.students.data.StudentRepository;
import com.example.students.data.StudentUnit;
import com.example.students.resource.StudentDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
class StudentResourceMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    void cleanUp() {
        studentRepository.deleteAll();
    }

    @Test
    void givenNotExistingStudentId_whenGetStudentById_thenRecordNotFoundExceptionIsThrown() throws Exception {
        var studentId = UUID.randomUUID();

        var response = mockMvc.perform(get("/students/" + studentId))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("not found")))
                .andReturn()
                .getResponse();

        assertEquals(response.getStatus(), 404);
        assertTrue(response.getContentAsString().contains("not found"));
    }

    @Test
    void givenNoStudentsWhenGetByNameThenReturnEmptyList() throws Exception {
        mockMvc.perform(get("/students?name=Karola"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

    @Test
    void givenStudentsWithNameWhenGetByNameThenReturnValidList() throws Exception {
        var student1 = new Student("Karola", StudentUnit.GDANSK, 15L);
        var student2 = new Student("Karola", StudentUnit.WARSZAWA, 25L);
        var student3 = new Student("Jan", StudentUnit.GDANSK, 17L);
        studentRepository.saveAll(List.of(student1, student2, student3));

        var response = mockMvc.perform(get("/students?name=Karola"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        var returnedStudents = objectMapper.readValue(response.getContentAsString(), new TypeReference<List<StudentDto>>() {
        });

        assertEquals(returnedStudents.size(), 1);
        assertEquals(returnedStudents.get(0).getName(), "Karola");
        assertEquals(returnedStudents.get(0).getUnit(), StudentUnit.GDANSK);
    }


    @Test
    void givenStudentsWithNameWhenDeleteByNameThenDeleteStudents() throws Exception {
        var student1 = new Student("Karola", StudentUnit.GDANSK, 15L);
        var student2 = new Student("Karola", StudentUnit.WARSZAWA, 25L);
        var student3 = new Student("Jan", StudentUnit.GDANSK, 17L);
        studentRepository.saveAll(List.of(student1, student2, student3));

        mockMvc.perform(delete("/students?name=Karola"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        var students = studentRepository.findAll();
        assertEquals(students.size(), 1);
        assertEquals(students.get(0).getName(), "Jan");
    }

    @Test
    void givenNoStudentsWhenDeleteByNameThenReturnNotFound() throws Exception {
        mockMvc.perform(delete("/students?name=Karola"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}