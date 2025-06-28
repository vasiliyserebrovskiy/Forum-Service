package ait.cohort5860.accounting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Vasilii Serebrovskii
 * @version 1.0 (27.06.2025)
 */
@Getter
public class RegisterUserDto {
            private String login;
            private String password;
            private String firstName;
            private String lastName;
}
