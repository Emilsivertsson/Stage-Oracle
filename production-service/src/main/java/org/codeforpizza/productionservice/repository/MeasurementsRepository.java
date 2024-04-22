package org.codeforpizza.productionservice.repository;


import org.codeforpizza.productionservice.modell.entitys.Measurements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurements, Long> {
}
