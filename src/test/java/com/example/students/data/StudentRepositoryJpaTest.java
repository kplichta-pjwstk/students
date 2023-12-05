package com.example.students.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class StudentRepositoryJpaTest {

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void setup() {
        var student1 = new Student("Karola", StudentUnit.GDANSK, 15L);
        var student2 = new Student("Aga", StudentUnit.GDANSK, 50L);
        studentRepository.save(student1);
        studentRepository.save(student2);
//        studentRepository.saveAll(List.of(student1, student2));
    }

    @Test
    void givenStudentsInDb_whenGetMaxIndex_ThenReturnMaxIndex() {
        var indexOptional = studentRepository.findMaxIndex();

        assertTrue(indexOptional.isPresent());
        assertEquals(indexOptional.get(), 50L);
    }
}
