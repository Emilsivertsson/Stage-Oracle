package org.codeforpizza.registrationservice.repository;


import org.codeforpizza.registrationservice.models.Measurements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurements, Long> {
}
