package com.example.students;

import com.example.students.exception.ResourceNotFoundException;
import com.example.students.resource.StudentsResource;
import com.example.students.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StudentsResource.class)
public class StudentResourceWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

//    @MockBean
//    private StudentRepository repository;
//    private StudentMapper mapper = new StudentMapper();
//    @SpyBean
//    private StudentService service = new StudentService(repository, mapper);

    @Test
    void givenNotExistingStudentId_whenGetStudentById_thenRecordNotFoundExceptionIsThrown() throws Exception {
        var studentId = UUID.randomUUID();
        when(studentService.getStudentById(any())).thenThrow(new ResourceNotFoundException("not found"));

        var response = mockMvc.perform(get("/students/" + studentId))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("not found")))
                .andReturn()
                .getResponse();

        assertEquals(response.getStatus(), 404);
        assertTrue(response.getContentAsString().contains("not found"));
    }
}
