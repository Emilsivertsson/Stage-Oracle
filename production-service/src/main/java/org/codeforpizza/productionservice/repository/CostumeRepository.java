package org.codeforpizza.productionservice.repository;

import org.codeforpizza.productionservice.modell.entitys.Costume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostumeRepository extends JpaRepository<Costume, Long> {
}
