package com.soundinstantz.application.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data Transfer Object for Cloze Test questions
 * <p>
 * Represents fill-in-the-blank exercises generated from text analysis.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ClozeQuestionDto {

    @NotBlank(message = "Sentence cannot be blank")
    private String sentence;

    @NotBlank(message = "Correct answer cannot be blank")
    private String correctAnswer;

    private String explanation;

    @NotNull(message = "Options cannot be null")
    @Size(min = 2, max = 6, message = "Must have between 2 and 6 options")
    private List<String> options;
}
