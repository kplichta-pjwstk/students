package com.example.students.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentPaymentData {

    @Id
    @GeneratedValue
    private UUID id;
    private String IBAN;

    @OneToOne(mappedBy = "paymentData") // relacja mapowana dwustronnie (będąca w obu encjach) musi zawierać mappedBy, żeby dane nie pobierały się w pętli student -> paymentData -> student -> ...
    //mappedBy wskazuje na nazwę tego propertisu w encji bazowej
    private Student student;
}
