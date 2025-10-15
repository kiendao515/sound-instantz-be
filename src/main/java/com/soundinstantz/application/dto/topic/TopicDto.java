package com.soundinstantz.application.dto.topic;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Topic operations
 * <p>
 * Used for API communication and follows the JSON structure
 * expected by the frontend application.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopicDto {

    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    @JsonProperty("name")
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    @JsonProperty("description")
    private String description;

    private String createdAt;
    private String updatedAt;
}
