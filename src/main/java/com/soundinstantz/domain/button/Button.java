package com.soundinstantz.domain.button;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "buttons")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Button {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "sound_file_url", nullable = false)
    private String soundFileUrl;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "play_count")
    private Long playCount = 0L;

    @Column(name = "download_count")
    private Long downloadCount = 0L;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
