package ait.cohort5860.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Vasilii Serebrovskii
 * @version 1.0 (25.06.2025)
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddCommentDto {
    @NotBlank(message = "Message is required.")
    @Size(min = 10, max = 1000, message = "Message must be between 10 and 1000 characters")
    private String message;
}
