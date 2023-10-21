package com.example.students.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Entity // ta adnotacja informuje nas oraz spring boota o tym, że jest to Javovy model tabeli w bazie ( w tym wypadku tabeli student) i na ten obiekt powinno zostać wykonane mapowanie
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED) // bezparametrowy konstruktor jest wymagany do tworzenia tych obiektów podczas operacji na bazie, ustawnienia access na protected zapewnia nam dostęp tego konstruktora dla hibernate, a jednocześnie zamyka ten konstruktor na dostęp z zewnątrz
public class Student {

    @Id //jak tabela w bazie tak samo każda encja musi mieć id
    @GeneratedValue //ta adnotacja informuje nas o tym, że wartość pola id będzie generowana automatycznie i pole to nie powinno być uzupełniane przez apliakcje przy tworzeniu obiektu
    private UUID id;
    private String name;
    private StudentUnit unit;
    private Long index;
}
