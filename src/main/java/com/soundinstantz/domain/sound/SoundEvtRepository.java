package com.soundinstantz.domain.sound;

import com.soundinstantz.util.Const;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoundEvtRepository extends JpaRepository<SoundEvtTracking, Long> {
    int countByUserIdAndSoundId(Long userId, Long soundId);
    List<SoundEvtTracking> findAllByUserIdAndEventType(Long userId, String event);
    int countBySoundIdAndEventType(Long soundId, String event);
    
    boolean existsByEventTypeAndSoundIdAndUserId(
        String eventType,
        Long soundId,
        Long userId
    );
    
    void deleteByEventTypeAndSoundIdAndUserId(
        String eventType,
        Long soundId,
        Long userId
    );
    
    List<SoundEvtTracking> findByUserIdAndEventType(
        Long userId,
        String eventType
    );
}
