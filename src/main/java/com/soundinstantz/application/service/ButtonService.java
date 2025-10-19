package com.soundinstantz.application.service;

import com.soundinstantz.application.dto.ButtonDTO;
import com.soundinstantz.domain.button.Button;
import com.soundinstantz.domain.button.ButtonRepository;
import com.soundinstantz.domain.user.User;
import com.soundinstantz.domain.user.UserRepository;
import com.soundinstantz.application.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ButtonService {
    private final ButtonRepository buttonRepository;
    private final UserRepository userRepository;

    public Page<ButtonDTO> getTrendingButtons(Pageable pageable, Long userId) {
        Page<Button> buttons = buttonRepository.findAllByOrderByPlayCountDesc(pageable);
        return buttons.map(button -> convertToDTO(button, userId));
    }

    @Transactional
    public ButtonDTO getButtonDetails(Long buttonId, Long userId) {
        Button button = buttonRepository.findById(buttonId)
                .orElseThrow(() -> new ResourceNotFoundException("Button not found"));
        return convertToDTO(button, userId);
    }

    @Transactional
    public void incrementPlayCount(Long buttonId) {
        Button button = buttonRepository.findById(buttonId)
                .orElseThrow(() -> new ResourceNotFoundException("Button not found"));
        button.setPlayCount(button.getPlayCount() + 1);
        buttonRepository.save(button);
    }

    @Transactional
    public void incrementDownloadCount(Long buttonId) {
        Button button = buttonRepository.findById(buttonId)
                .orElseThrow(() -> new ResourceNotFoundException("Button not found"));
        button.setDownloadCount(button.getDownloadCount() + 1);
        buttonRepository.save(button);
    }

    private ButtonDTO convertToDTO(Button button, Long userId) {
        boolean isFavorite = false;
        if (userId != null) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            //isFavorite = user.getFavoriteButtons().contains(button);
        }

        return ButtonDTO.builder()
                .id(button.getId())
                .name(button.getName())
                .description(button.getDescription())
                .soundFileUrl(button.getSoundFileUrl())
                .imageUrl(button.getImageUrl())
                .playCount(button.getPlayCount())
                .downloadCount(button.getDownloadCount())
                .createdAt(button.getCreatedAt())
                .isFavorite(isFavorite)
                .build();
    }
}
