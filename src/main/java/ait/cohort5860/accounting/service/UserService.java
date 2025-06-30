package ait.cohort5860.accounting.service;

import ait.cohort5860.accounting.dto.RolesDto;
import ait.cohort5860.accounting.dto.UserDto;

public interface UserService {

    UserDto registerNewUser(String login, String password, String firstName, String lastName);

    UserDto deleteUser(String login);

    UserDto updateUser(String login, String firstName, String lastName);

    RolesDto changeRolesList(String login, String role, boolean isAddRole);

    UserDto getUserByLogin(String login);

}
