package org.eduaid;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

	Volunteer findByEmail(String userEmail);


}
