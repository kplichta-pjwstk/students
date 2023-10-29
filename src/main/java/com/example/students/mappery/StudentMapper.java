package com.example.students.mappery;

import com.example.students.data.Student;
import com.example.students.resource.CreateStudent;
import com.example.students.resource.StudentDto;
import org.springframework.stereotype.Component;

//nasze dto muszą zostać w jakiś sposób zmapowane na encje i my robimy to ręcznie tworząc obiekty
@Component
public class StudentMapper {

    public StudentDto toDto(Student student) {
        return new StudentDto(
                student.getId(),
                student.getName(),
                student.getUnit(),
                student.getIndex()
        );
    }

    public Student toEntity(CreateStudent createStudent) {
        return new Student(createStudent.getName(), createStudent.getUnit());
    }
}
