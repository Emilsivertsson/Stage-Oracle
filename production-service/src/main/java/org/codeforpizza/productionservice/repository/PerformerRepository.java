package org.codeforpizza.productionservice.repository;

import org.codeforpizza.productionservice.modell.Performer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformerRepository extends JpaRepository<Performer, Long> {
}
