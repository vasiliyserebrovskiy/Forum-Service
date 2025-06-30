package ait.cohort5860.accounting.controller;

import ait.cohort5860.accounting.dto.RegisterUserDto;
import ait.cohort5860.accounting.dto.RolesDto;
import ait.cohort5860.accounting.dto.UpdateUserDto;
import ait.cohort5860.accounting.dto.UserDto;
import ait.cohort5860.accounting.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Vasilii Serebrovskii
 * @version 1.0 (28.06.2025)
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public UserDto registerNewUser(@RequestBody RegisterUserDto registerUserDto) {
        return userService.registerNewUser(registerUserDto);
    }

    @PostMapping("/login")
    public UserDto login() {
        // TODO
        return null;
    }

    @DeleteMapping("/user/{login}")
    public UserDto deleteUser(@PathVariable String login) {
        return userService.deleteUser(login);
    }

    @PatchMapping("/user/{login}")
    public UserDto updateUser(@PathVariable String login, @RequestBody UpdateUserDto updateUserDto) {
        return userService.updateUser(login, updateUserDto);
    }

    @PatchMapping("/user/{login}/role/{role}")
    public RolesDto addRole(@PathVariable String login, @PathVariable String role) {
        return userService.changeRolesList(login, role, true);
    }

    @DeleteMapping("/user/{login}/role/{role}")
    public RolesDto deleteRole(@PathVariable String login, @PathVariable String role) {
        return userService.changeRolesList(login, role, false);
    }

    @PatchMapping("/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword() {
        // TODO
    }

    @GetMapping("/user/{login}")
    public UserDto getUserByLogin(@PathVariable String login) {
        return userService.getUserByLogin(login);
    }
}
