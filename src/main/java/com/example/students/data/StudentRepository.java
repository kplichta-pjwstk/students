package com.example.students.data;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentRepository extends CrudRepository<Student, UUID> {

    @Modifying
    @Transactional
    void deleteByName(String name);

    @Query("select max(s.index) from Student s group by s.id")
    Long findMaxIndex();
}
