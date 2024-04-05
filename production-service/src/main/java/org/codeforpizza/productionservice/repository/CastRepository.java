package org.codeforpizza.productionservice.repository;

import org.codeforpizza.productionservice.modell.Cast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CastRepository extends JpaRepository<Cast, Long> {
}
