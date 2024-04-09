package org.codeforpizza.productionservice.repository;

import org.codeforpizza.productionservice.modell.entitys.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionRepository extends JpaRepository<Production, Long> {
}
