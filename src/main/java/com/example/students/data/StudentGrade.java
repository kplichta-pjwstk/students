package com.example.students.data;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentGrade {

    @Id
    @GeneratedValue
    private UUID id;
    private Long grade;
    private String subject;

    @ManyToOne // mapowanie relacji wiele do jednego
    @JoinColumn(table = "student", name = "student_id", referencedColumnName = "id") // definicja kolumny łączącej, może być stosowana przy @OneToOne, @OneToMany, @ManyToOne
    // w tym przypadku jest ona zbędna - dane zostaną wygenerowane w ten sposób, jeśli jednak łączenie odbywałoby się inaczej musimy opisać je poprzez tą adnotację.
    private Student student;
}
