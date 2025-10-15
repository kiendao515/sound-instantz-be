package com.soundinstantz.interfaces.rest;

import com.soundinstantz.application.dto.ButtonDTO;
import com.soundinstantz.application.service.ButtonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buttons")
@RequiredArgsConstructor
public class ButtonController {
    private final ButtonService buttonService;

    @GetMapping("/trending")
    public ResponseEntity<Page<ButtonDTO>> getTrendingButtons(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestHeader(value = "User-Id", required = false) Long userId) {
        return ResponseEntity.ok(buttonService.getTrendingButtons(PageRequest.of(page, size), userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ButtonDTO> getButtonDetails(
            @PathVariable Long id,
            @RequestHeader(value = "User-Id", required = false) Long userId) {
        return ResponseEntity.ok(buttonService.getButtonDetails(id, userId));
    }

    @PostMapping("/{id}/play")
    public ResponseEntity<Void> recordPlay(@PathVariable Long id) {
        buttonService.incrementPlayCount(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/download")
    public ResponseEntity<Void> recordDownload(@PathVariable Long id) {
        buttonService.incrementDownloadCount(id);
        return ResponseEntity.ok().build();
    }
}
