package com.example.students.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.Instant;

//Definujemy specjalny rodzaj beana, który pozwoli nam obsługiwać wyjątki i tłumaczyć je
//na kody http. Żeby dać znać Springowi, że ten bean będzie się tym zajmował korzystamy z adnotacji: @ControllerAdvice
@ControllerAdvice
public class ExceptionResponseControllerAdvice {

    //Kluczowa adnotacja nad metodą to @ExceptionHandler - informuje springa
    //jakie klasy wyjątków mają zostać obsłużone w tej metodzie
    //Aby możemy zdefiniować reakcję na wyjątek jak poniżej ustawiając treść body
    //odpowiedzi oraz jej status przez adnotacje
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {ResourceNotFoundException.class, StudentNotFoundException.class})
    @ResponseBody
    public ErrorResponse handeNotFound(RuntimeException exception) {
        return new ErrorResponse(Instant.now(), exception.getMessage());
    }

//    Zakomentowana alternatywna wersja tego zapisu - dla jednego Exceptiona powinniśmy mieć
//    jeden exception handler a więc jeśli chcecie korzystać z metody poniżej musicie zakomentować metodę powyżej

//    @ExceptionHandler(value = ResourceNotFoundException.class)
//    public ResponseEntity<ErrorResponse> handeNotFound(RuntimeException exception) {
//        var responseBody = new ErrorResponse(Instant.now(), exception.getMessage());
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .body(responseBody);
//    }
}
