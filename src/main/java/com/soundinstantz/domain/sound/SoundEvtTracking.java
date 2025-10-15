package com.soundinstantz.domain.sound;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "sound_events")
@Data
@Builder
public class SoundEvtTracking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sound_id", nullable = false)
    private Long soundId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "event_type", nullable = false)
    private String eventType;
}
