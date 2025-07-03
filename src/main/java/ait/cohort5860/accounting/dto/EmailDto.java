package ait.cohort5860.accounting.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import javax.swing.*;

/**
 * @author Vasilii Serebrovskii
 * @version 1.0 (03.07.2025)
 */
@Getter
public class EmailDto {
    @NotBlank(message = "To field is required")
    @Email(message = "To field must be a valid email")
    private String to;
    @NotBlank(message = "Subject field is required")
    private String subject;
    @NotBlank(message = "Message field is required")
    private String message;
}
