package com.soundinstantz.application.dto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ButtonDTO {
    private Long id;
    private String name;
    private String description;
    private String soundFileUrl;
    private String imageUrl;
    private Long playCount;
    private Long downloadCount;
    private LocalDateTime createdAt;
    private boolean isFavorite;
}
