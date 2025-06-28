package ait.cohort5860.accounting.service;

import ait.cohort5860.accounting.dto.ResponseAddRoleDto;
import ait.cohort5860.accounting.dto.UserDto;

public interface UserService {

    UserDto registerNewUser(String login, String password, String firstName, String lastName);

    // login method

    UserDto deleteUser(String login);

    UserDto updateUser(String login, String firstName, String lastName);

    ResponseAddRoleDto addRole(String login, String roleName);

    ResponseAddRoleDto deleteRole(String login, String roleName);

    //changePassword method

    UserDto getUserByLogin(String login);

}
