package com.soundinstantz.domain.sound;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SoundRepository extends JpaRepository<Sound, Long>, JpaSpecificationExecutor<Sound> {
    Page<Sound> findAllByOrderByPlayCountDesc(Pageable pageable);
    Page<Sound> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
