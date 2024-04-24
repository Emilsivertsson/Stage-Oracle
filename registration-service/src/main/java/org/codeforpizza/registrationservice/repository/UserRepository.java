package org.codeforpizza.registrationservice.repository;

import com.fasterxml.jackson.databind.introspect.AnnotationCollector;
import org.codeforpizza.registrationservice.models.entitys.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
    ApplicationUser findByUsername(String username);

    boolean existsByUsername(String username);

    long deleteByUsernameIgnoreCase(String username);

}
