package org.eduaid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
    // Fetch media items by user email
    List<Media> findByUserEmail(String userEmail);
}
