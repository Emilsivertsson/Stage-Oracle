package org.codeforpizza.productionservice.repository;

import org.codeforpizza.productionservice.modell.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
    ApplicationUser findByUsername(String username);

    boolean existsByUsername(String username);

}
