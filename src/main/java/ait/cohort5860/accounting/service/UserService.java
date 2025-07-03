package ait.cohort5860.accounting.service;

import ait.cohort5860.accounting.dto.*;

public interface UserService {

    UserDto registerNewUser(RegisterUserDto registerUserDto);

    UserDto deleteUser(String login);

    UserDto updateUser(String login, UpdateUserDto updateUserDto);

    RolesDto changeRolesList(String login, String role, boolean isAddRole);

    UserDto getUserByLogin(String login);

    void changePassword(String login, String newPassword);

    void sendEmail(EmailDto emailDto);

}
