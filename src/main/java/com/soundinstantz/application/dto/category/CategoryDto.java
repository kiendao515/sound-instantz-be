package com.soundinstantz.application.dto.category;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {
    private Long id;
    private String title;
    private String slug;
    private String description;
}
