package org.codeforpizza.registrationservice.repository;

import org.codeforpizza.registrationservice.models.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
    ApplicationUser findByUsername(String username);

    boolean existsByUsername(String username);

    long deleteByUsernameIgnoreCase(String username);
}
