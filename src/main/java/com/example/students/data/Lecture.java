package com.example.students.data;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lecture {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

}
