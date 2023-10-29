package com.example.students.resource;

import com.example.students.data.StudentUnit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class StudentDto {

    private UUID id;
    private String name;
    private StudentUnit unit;
    private Long index;
}
