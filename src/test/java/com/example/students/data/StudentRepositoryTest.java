package com.example.students.data;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Deprecated
class StudentRepositoryTest {

    @Test
    void givenEmptyStudentsListWhenGetMaxIndexThenReturnZero() {
        List<Student> students = Collections.emptyList();
        var repository = new StudentListContainer();
        repository.setStudents(students);

        var index = repository.findMaxIndex();

        assertEquals(0L, index);
    }

}