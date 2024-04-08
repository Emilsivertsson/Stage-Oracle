package org.codeforpizza.registrationservice.repository;


import org.codeforpizza.registrationservice.models.Performer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerformerRepository extends JpaRepository<Performer, Long> {


}
