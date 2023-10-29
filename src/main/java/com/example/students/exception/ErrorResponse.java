package com.example.students.exception;

import java.time.Instant;

public record ErrorResponse (Instant occurrenceTime, String message) {
}
