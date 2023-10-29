package com.example.students.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//jeśli chcemy jedynie uzyskać zmianę statusu odpowiedzi w przypadku błędu możemy
//dodać adnotację tylko nad klasą wyjątku, bez potrzeby umieszczania jej w ControllerAdvice'orze
@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotAllowedOperationException extends RuntimeException{
}
