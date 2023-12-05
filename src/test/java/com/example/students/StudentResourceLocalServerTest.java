package com.example.students;

import com.example.students.exception.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatusCode;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentResourceLocalServerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void givenNotExistingStudentId_whenGetStudentById_thenRecordNotFoundExceptionIsThrown() throws Exception {
        var studentId = UUID.randomUUID();

        var responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/students/" + studentId, ErrorResponse.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatusCode.valueOf(404));
        assertTrue(responseEntity.getBody().message().contains("not found"));
        assertNotNull(responseEntity.getBody().occurrenceTime());
    }
}
