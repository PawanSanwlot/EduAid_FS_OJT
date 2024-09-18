package org.eduaid;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DonorRepository extends JpaRepository<Donor, Long> {
	Donor findByEmail(String email);
}
