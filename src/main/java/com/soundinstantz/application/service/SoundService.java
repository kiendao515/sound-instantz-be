package com.soundinstantz.application.service;

import com.soundinstantz.application.dto.category.CategoryDto;
import com.soundinstantz.application.dto.sound.SoundDTO;
import com.soundinstantz.application.exception.BizException;
import com.soundinstantz.application.exception.ResourceNotFoundException;
import com.soundinstantz.domain.category.Category;
import com.soundinstantz.domain.category.CategoryRepository;
import com.soundinstantz.domain.sound.Sound;
import com.soundinstantz.domain.sound.SoundEvtRepository;
import com.soundinstantz.domain.sound.SoundEvtTracking;
import com.soundinstantz.domain.sound.SoundRepository;
import com.soundinstantz.domain.user.User;
import com.soundinstantz.util.Const;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;


@Service
public class SoundService {
    private final SoundRepository soundRepository;
    private final SoundEvtRepository soundEvtRepository;
    private final CategoryRepository categoryRepository;

    public SoundService(SoundRepository soundRepository, SoundEvtRepository soundEvtRepository, CategoryRepository categoryRepository) {
        this.soundRepository = soundRepository;
        this.soundEvtRepository = soundEvtRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public Page<SoundDTO> getSounds(String categorySlug, String search, Pageable pageable) {
        Specification<Sound> spec = Specification.where(null);
        
        if (StringUtils.hasText(categorySlug)) {
            Category category = categoryRepository.findBySlug(categorySlug)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + categorySlug));

            spec = spec.and((root, query, cb) ->
                cb.equal(root.get("categoryId"), category.getId()));
        }
        
        if (StringUtils.hasText(search)) {
            spec = spec.and((root, query, cb) ->
                cb.or(
                    cb.like(cb.lower(root.get("name")), "%" + search.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("description")), "%" + search.toLowerCase() + "%")
                ));
        }
        
        Page<Sound> sounds = soundRepository.findAll(spec, pageable);
        return sounds.map(sound -> {
            try {
                return convertToDTO(sound);
            } catch (BizException e) {
                throw new RuntimeException("Error converting sound to DTO: " + e.getMessage(), e);
            }
        });
    }

    @Transactional
    public SoundDTO getSoundDetails(Long id) throws BizException {
        Sound sound = soundRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sound not found"));
        return convertToDTO(sound);
    }

    public SoundDTO toggleHeart(Long id, User user) throws BizException{
        Sound sound = soundRepository.findById(id)
                .orElseThrow(() -> new BizException("Sound not found"));
        SoundDTO dto = convertToDTO(sound);
        boolean hasLiked = hasLiked(id, user.getId());

        if (hasLiked) {
            soundEvtRepository.deleteByEventTypeAndSoundIdAndUserId(
                    Const.SoundEventType.LIKE,
                    id,
                    user.getId()
            );
            dto.setLiked(false);
//            sound.setLikeCount(sound.getLikeCount() - 1);
        } else {
            SoundEvtTracking soundEvt = SoundEvtTracking.builder().soundId(id).userId(user.getId())
                    .eventType(Const.SoundEventType.LIKE)
                    .createdAt(LocalDateTime.now())
                    .build();
            soundEvtRepository.save(soundEvt);
            dto.setLiked(true);
//            sound.setLikeCount(sound.getLikeCount() + 1);
        }
        return dto;
    }

    public boolean hasLiked(Long soundId, Long userId) {
        return soundEvtRepository.existsByEventTypeAndSoundIdAndUserId(
                Const.SoundEventType.LIKE,
                soundId,
                userId
        );
    }

    @Transactional
    public void incrementPlayCount(Long id) {
        Sound sound = soundRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sound not found"));
        sound.setPlayCount(sound.getPlayCount() + 1);
        soundRepository.save(sound);
    }

    @Transactional
    public boolean toggleLike(Long id) {
        // TODO: Implement like functionality with user context
        return true;
    }

    @Transactional(readOnly = true)
    public long getLikesCount(Long id) throws BizException {
        Sound sound = soundRepository.findById(id)
                .orElseThrow(() -> new BizException("Sound not found"));
        return soundEvtRepository.countBySoundIdAndEventType(sound.getId(), Const.SoundEventType.LIKE);

    }

    private SoundDTO convertToDTO(Sound sound) throws BizException {
        Category c = categoryRepository.findById(sound.getCategoryId())
                .orElseThrow(() -> new BizException("Category not found"));
        return SoundDTO.builder()
                .id(sound.getId())
                .name(sound.getName())
                .description(sound.getDescription())
                .fileUrl(sound.getFileUrl())
                .color(sound.getColor())
                .likeCount(getLikesCount(sound.getId()))
                .viewed(sound.getViewed())
                .categoryDto(CategoryDto.builder()
                        .id(c.getId())
                        .title(c.getTitle())
                        .description(c.getDescription())
                        .slug(c.getSlug())
                        .build())
                .tags(sound.getTags())
                .build();
    }
}
