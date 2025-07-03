package ait.cohort5860.accounting.service;

import ait.cohort5860.accounting.dao.UserAccountRepository;
import ait.cohort5860.accounting.dto.*;
import ait.cohort5860.accounting.dto.exceptions.InvalidDataException;
import ait.cohort5860.accounting.dto.exceptions.UserExistsException;
import ait.cohort5860.accounting.dto.exceptions.UserNotFoundException;
import ait.cohort5860.accounting.model.Role;
import ait.cohort5860.accounting.model.UserAccount;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Vasilii Serebrovskii
 * @version 1.0 (30.06.2025)
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, CommandLineRunner {

    private final UserAccountRepository userAccountRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;

    @Override
    public UserDto registerNewUser(RegisterUserDto registerUserDto) {
        if(userAccountRepository.existsById(registerUserDto.getLogin())) {
            throw new UserExistsException();
        }
        UserAccount userAccount = modelMapper.map(registerUserDto, UserAccount.class);
        userAccount.addRole("USER");
        String encodedPassword = passwordEncoder.encode(registerUserDto.getPassword()); // hashing the password
        userAccount.setPassword(encodedPassword); // change password before saving
        userAccountRepository.save(userAccount);
        return modelMapper.map(userAccount, UserDto.class);
    }

    @Override
    public UserDto deleteUser(String login) {
        UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(UserNotFoundException::new);
        userAccountRepository.delete(userAccount);
        return modelMapper.map(userAccount, UserDto.class);
    }

    @Override
    public UserDto updateUser(String login, UpdateUserDto updateUserDto) {
        UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(UserNotFoundException::new);
        if (updateUserDto.getFirstName() != null) {
            userAccount.setFirstName(updateUserDto.getFirstName());
        }
        if (updateUserDto.getLastName() != null) {
            userAccount.setLastName(updateUserDto.getLastName());
        }
        userAccountRepository.save(userAccount); // this is not relation database no transactions
        return modelMapper.map(userAccount, UserDto.class);
    }

    @Override
    public RolesDto changeRolesList(String login, String role, boolean isAddRole) {
        UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(UserNotFoundException::new);
        try {
            if (isAddRole) {
                userAccount.addRole(role);
            } else {
                userAccount.removeRole(role);
            }
        } catch (Exception e) {
            throw new InvalidDataException();
        }
        userAccountRepository.save(userAccount);
        return modelMapper.map(userAccount, RolesDto.class);
    }

    @Override
    public UserDto getUserByLogin(String login) {
        UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(UserNotFoundException::new);
        return modelMapper.map(userAccount, UserDto.class);
    }

    @Override
    public void changePassword(String login, String newPassword) {
        UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(UserNotFoundException::new);
        userAccount.setPassword(passwordEncoder.encode(newPassword));
        userAccountRepository.save(userAccount);
    }

    @Override
    public void sendEmail(EmailDto emailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailDto.getTo());
        message.setSubject(emailDto.getSubject());
        message.setText(emailDto.getMessage());
        mailSender.send(message);
    }

    @Override
    public void run(String... args) throws Exception {
        if (!userAccountRepository.existsById("admin")) {
            UserAccount admin = UserAccount.builder()
                    .login("admin")
                    .password(passwordEncoder.encode("admin"))
                    .firstName("Admin")
                    .lastName("Admin")
                    .role(Role.USER)
                    .role(Role.MODERATOR)
                    .role(Role.ADMINISTRATOR)
                    .build();
            userAccountRepository.save(admin);
        }
    }
}
