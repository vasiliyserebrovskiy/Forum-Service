package ait.cohort5860.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author Vasilii Serebrovskii
 * @version 1.0 (25.06.2025)
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddPostDto {
    @NotBlank(message = "Title is required.")
    @Size(min = 15, max = 200, message = "Title must be between 15 and 200 characters")
    private String title;
    @NotBlank(message = "Content is required.")
    @Size(min = 20, max = 10000, message = "Content must be between 20 and 10000 characters")
    private String content;
    private Set<@NotBlank(message = "Tag cannot be blank") String> tags;
}
