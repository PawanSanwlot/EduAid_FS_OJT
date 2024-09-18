package org.eduaid;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrustRepository extends JpaRepository<Trust, Long> {
	Trust findByEmail(String email);
}
