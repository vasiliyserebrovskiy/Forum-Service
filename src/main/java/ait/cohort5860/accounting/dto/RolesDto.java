package ait.cohort5860.accounting.dto;

import lombok.*;

import java.util.Set;

/**
 * @author Vasilii Serebrovskii
 * @version 1.0 (28.06.2025)
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolesDto {
    private String login;
    @Singular
    private Set<String> roles;
}
