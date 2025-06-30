package ait.cohort5860.accounting.dto;

import lombok.*;

import java.util.Set;

/**
 * @author Vasilii Serebrovskii
 * @version 1.0 (27.06.2025)
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
     private String login;
     private String firstName;
     private String lastName;
     @Singular
     private Set<String> roles;
}
