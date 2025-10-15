package com.soundinstantz.domain.sound;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoundEvtRepository extends JpaRepository<SoundEvtTracking, Long> {
    int countByUserIdAndSoundId(Long userId, Long soundId);
    int countBySoundIdAndEventType(Long soundId, String event);
}
