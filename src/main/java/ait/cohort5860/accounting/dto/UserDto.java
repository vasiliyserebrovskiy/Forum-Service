package ait.cohort5860.accounting.dto;

import lombok.Getter;

import java.util.Set;

/**
 * @author Vasilii Serebrovskii
 * @version 1.0 (27.06.2025)
 */
@Getter
public class UserDto {
     private String login;
     private String firstName;
     private String lastName;
     private Set<AddRoleDto> roles;

}
