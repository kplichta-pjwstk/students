package com.example.students.data;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Entity // ta adnotacja informuje nas oraz spring boota o tym, że jest to Javovy model tabeli w bazie ( w tym wypadku tabeli student) i na ten obiekt powinno zostać wykonane mapowanie
@Table // tej adnotacji używamy gdy tabela ma inną nazwę lub położenie inne niż domyślne schema - public, name - w konwencji spring (hibernate) np.student_unit dla klasy StudentUnit
// powyższa adnotacja jest też użyteczna do zdefiniowania indeksów na bazie danych
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED) // bezparametrowy konstruktor jest wymagany do tworzenia tych obiektów podczas operacji na bazie, ustawnienia access na protected zapewnia nam dostęp tego konstruktora dla hibernate, a jednocześnie zamyka ten konstruktor na dostęp z zewnątrz
public class Student {

    @Id //jak tabela w bazie tak samo każda encja musi mieć id
    @GeneratedValue //ta adnotacja informuje nas o tym, że wartość pola id będzie generowana automatycznie i pole to nie powinno być uzupełniane przez apliakcje przy tworzeniu obiektu
    private UUID id;
    private String name;
    @Column // analogicznie do tabeli możemy sterować tu definicją i constraintami na konkretnych kolumnach
    @Enumerated(EnumType.STRING)
    private StudentUnit unit;
    @Setter
    private Long index;
    @OneToOne // mapujemy relacje jeden do jeden, encja w której ją definiujemy jest wiodącą
    //oznacza to, że id relacji (payment_data_id) będzie w tabeli student i będzie posiadało constraint unique
    private StudentPaymentData paymentData;

    @OneToMany(mappedBy = "student") // mapowanie jeden do wielu
    //Analogicznie jak przy one to one musimy dodać tu mapped by - dodajemy tylko w relacjach obustronnych przy adnotacjach OneTo...
    private List<StudentGrade> grades;

    @ManyToMany//przykład łączenia relacji wiele do wielu
    //ta adnotacja opisuje tabele łączącą, która powstanie przy automatycznej generacji
    //nie jest potrzebna, dodałam ją żeby wam pokazać jak użyć tej adnotacji
    //należy jej używać zawsze, gdy nasza tabela łącząca w wygląda inaczej niż ta poniżej
    //żeby spring(hibernate) wiedział jak poprawnie zmapować relację
    //błąd, który popełniłam na zajęciach - nie użyłam inverseJoinColumns = kolumny z klasy dołączanej, joinColumns - kolumny z klasy bazowej, można dołączyć więcej niż jedną
    @JoinTable(name = "student_lecture",
            joinColumns = @JoinColumn(table = "student", name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(table = "lecture", name = "lecture_id", referencedColumnName = "id")
    )
    private List<Lecture> lecture;

    public Student(String name, StudentUnit unit) {
        this.name = name;
        this.unit = unit;
    }
}
