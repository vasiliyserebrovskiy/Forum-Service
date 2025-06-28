package ait.cohort5860.accounting.dto;

import lombok.Getter;

import java.util.Set;

/**
 * @author Vasilii Serebrovskii
 * @version 1.0 (28.06.2025)
 */
@Getter
public class ResponseAddRoleDto {
    private String login;
    private Set<AddRoleDto> roles;
}
