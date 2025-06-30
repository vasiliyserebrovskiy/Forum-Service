package ait.cohort5860.accounting.controller;

import ait.cohort5860.accounting.dto.RolesDto;
import ait.cohort5860.accounting.dto.UserDto;
import ait.cohort5860.accounting.service.UserService;
import lombok.RequiredArgsConstructor;
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
    public UserDto registerNewUser(@RequestBody String login, @RequestBody String password, @RequestBody String firstName, @RequestBody String lastName) {
        return userService.registerNewUser(login, password, firstName, lastName);
    }

    // here must be login method

    @DeleteMapping("/user/{login}")
    public UserDto deleteUser(@PathVariable String login) {
        return userService.deleteUser(login);
    }

    @PatchMapping("/user/{login}")
    public UserDto updateUser(@PathVariable String login, @RequestBody String firstName, @RequestBody String lastName) {
        return userService.updateUser(login, firstName, lastName);
    }

    @PatchMapping("/user/{login}/role/{role}")
    public RolesDto addRole(@PathVariable String login, @PathVariable String role) {
        return userService.addRole(login, role);
    }

    @DeleteMapping("/user/{login}/role/{role}")
    public RolesDto deleteRole(@PathVariable String login, @PathVariable String role) {
        return userService.deleteRole(login, role);
    }

    // here must be changePassword method

    @GetMapping("/user/{login}")
    public UserDto getUserByLogin(@PathVariable String login) {
        return userService.getUserByLogin(login);
    }
}
