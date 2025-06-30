package ait.cohort5860.accounting.dto.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Vasilii Serebrovskii
 * @version 1.0 (30.06.2025)
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
@NoArgsConstructor
public class InvalidDataException extends RuntimeException{
    public InvalidDataException(String message) {
        super(message);
    }
}
