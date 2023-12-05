package com.example.students.data;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository //ta adnotacja mówi springowi, że jest to definicja beana z warstwy JPA (warstwa będzie miała znaczenie przy testach)
//rozszerzenie tego interface o CrudRepository pozwala nam nie tworzyć jego implementacji - możemy zostawić to springowi, który na podstawie nazw metod oraz odpowienich adnotacji wygeneruje za nas implementacje
//możemy dodatkowo skorzystać z interface'ów PagingAndSortingRepository lub JpaRepository, jesli potrzebujemy dodatkowych funkcji udostępnianych przez te interface'y
public interface StudentRepository extends JpaRepository<Student, UUID> {

    @Modifying // dodajemy zawsze gdy zapytanie modyfikuje w jakiś sposób dane - w przypadku springa będą to query z delete lub update, choć sam update częściej realizowany jest przez obiekty z gotową metodą save (z interface CrudRepository), tak jak insert
    @Transactional // tą adnotacją otwieramy transakcje na bazie danych - mała uwaga nie musi ona znajdować się w repozytorium, może znajdować się w dowolnym miejscu w kodzie
    //znacznie częściej stosuje się ją w serwisach aby uniknąć otwierania wielu małych transakcji
    void deleteByName(String name);

    @Query("select max(s.index) from Student s") //query pozwala napisać nam bardziej skomplikowane zapytania, których spring nie jest w stanie wygenerować z metod
    Optional<Long> findMaxIndex();

    List<Student> findByName(String name);

    List<Student> findByUnitAndName(StudentUnit unit, String name);

    default List<Student> findFromGdanskByName(String name) {
        return findByUnitAndName(StudentUnit.GDANSK, name);
    }
}
