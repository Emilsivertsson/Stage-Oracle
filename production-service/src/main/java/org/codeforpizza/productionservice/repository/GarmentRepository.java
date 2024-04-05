package org.codeforpizza.productionservice.repository;

import org.codeforpizza.productionservice.modell.Garment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GarmentRepository extends JpaRepository<Garment, Long> {
}
