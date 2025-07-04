package ait.cohort5860.post.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * @author Vasilii Serebrovskii
 * @version 1.0 (04.07.2025)
 */
@Getter
public class FileDto {
    private Long id;
    @JsonProperty("filename")
    private String fileName;
}
