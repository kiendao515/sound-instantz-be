package com.soundinstantz.interfaces.rest;

import com.soundinstantz.application.dto.sound.SoundDTO;
import com.soundinstantz.application.exception.BizException;
import com.soundinstantz.application.service.SoundService;
import com.soundinstantz.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sounds")
@RequiredArgsConstructor
public class SoundController {
    private final SoundService soundService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<SoundDTO>>> getSounds(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int limit) {
        Page<SoundDTO> sounds = soundService.getSounds(category, search, PageRequest.of(page, limit));
        return ResponseEntity.ok(new ApiResponse<>(sounds.getContent()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SoundDTO>> getSound(@PathVariable String id) throws BizException {
        SoundDTO sound = soundService.getSoundDetails(Long.parseLong(id));
        return ResponseEntity.ok(new ApiResponse<>(sound));
    }

    @PostMapping("/{id}/views")
    public ResponseEntity<Void> incrementViews(@PathVariable String id) {
        soundService.incrementPlayCount(Long.parseLong(id));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<Map<String, Object>> toggleLike(@PathVariable String id) throws BizException {
        boolean liked = soundService.toggleLike(Long.parseLong(id));
        long likes = soundService.getLikesCount(Long.parseLong(id));
        return ResponseEntity.ok(Map.of("liked", liked, "likes", likes));
    }
}
