package ait.cohort5860.accounting.dto.exceptions;


/**
 * @author Vasilii Serebrovskii
 * @version 1.0 (29.06.2025)
 */
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
@NoArgsConstructor
public class UserExistsException extends RuntimeException {
    public UserExistsException(String message) {
        super(message);
    }
}
