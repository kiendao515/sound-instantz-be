package com.soundinstantz.domain.button;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ButtonRepository extends JpaRepository<Button, Long> {
    Page<Button> findAllByOrderByPlayCountDesc(Pageable pageable);
    Page<Button> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
