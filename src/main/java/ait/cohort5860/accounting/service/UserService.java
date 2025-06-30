package ait.cohort5860.accounting.service;

import ait.cohort5860.accounting.dto.RolesDto;
import ait.cohort5860.accounting.dto.UserDto;

public interface UserService {

    UserDto registerNewUser(String login, String password, String firstName, String lastName);

    // login method

    UserDto deleteUser(String login);

    UserDto updateUser(String login, String firstName, String lastName);

    RolesDto addRole(String login, String roleName);

    RolesDto deleteRole(String login, String roleName);

    //changePassword method

    UserDto getUserByLogin(String login);

}
