package com.soundinstantz.application.dto.sound;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SoundFavouriteDto {
    private List<Long> soundIds;
    private Long userId;
}
