package com.example.students.resource;

import com.example.students.exception.NotAllowedOperationException;
import com.example.students.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/students")
@RequiredArgsConstructor
public class StudentsResource {

    private final StudentService studentService;

    //Przyjmujemy Data transfer object, który służy nam do tworzenia studenta
    //zapewnia to ograniczenie udostępniania struktury i przyjmowanie tylko niezbędnych danych
    //co z kolei ogranicza możliwość zapisu danej z body, która nie powinna zostać wprowadzona przez użytkownika
    //jak np. index, który generuje aplikacja
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    //Informujemy Springa o tym, że powinien wywołać walidację przez dodanie adnotacji @Validated
    //dzięki temu automatycznie otrzymamy informację o tym, że pole jest błędnie wypełnione, a do tego odpowiedzi status odpowiedzi 400 BAD_REQUEST
    public void createStudent(@RequestBody @Validated CreateStudent student) {
        studentService.createStudent(student);
    }

    //W tym przypadku skorzystanie z DTO zapewnia nam, że w przypadku dodania technicznych propertisów w obiekcie
    //nie zostaną one udostępnione w odpowiedzi na zapytanie. Możemy dowolnie sterować tym co jest zwracane w odpowiedzi
    //bez konieczności modyfikowania encji czy struktury bazy danych
    @GetMapping("/{id}")
    public StudentDto getStudentById(@PathVariable UUID id) {
        return studentService.getStudentById(id);
    }

    @DeleteMapping
    public void deleteByName(String name) {
        if("admin".equals(name)){
            throw new NotAllowedOperationException();
        }
        studentService.deleteByName(name);
    }

    @GetMapping
    public List<StudentDto> getByName(@RequestParam String name) {
        return studentService.getNameBy(name);
    }
}
