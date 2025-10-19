package com.soundinstantz.application.dto.sound;

import com.soundinstantz.application.dto.category.CategoryDto;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SoundDTO {
    private Long id;
    private String name;
    private String color;
    private String description;
    private String fileUrl;
    private Long playCount;
    private Long downloadCount;
    private Long likeCount;
    private Long viewed;
    private String tags;
    private CategoryDto categoryDto;
    private boolean liked;
}
