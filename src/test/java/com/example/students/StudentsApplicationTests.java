package com.example.students;

import com.example.students.exception.ResourceNotFoundException;
import com.example.students.resource.StudentsResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class StudentsApplicationTests {

    @Autowired
    private StudentsResource resource;

    @Test
    void contextLoads() {
        assertNotNull(resource);
    }

    @Test
    void givenNotExistingStudentId_whenGetStudentById_thenRecordNotFoundExceptionIsThrown() {
        var studentUid = UUID.randomUUID();

        assertThrows(ResourceNotFoundException.class, () -> resource.getStudentById(studentUid));
    }

}
