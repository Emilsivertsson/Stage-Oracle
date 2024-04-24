package org.codeforpizza.registrationservice.repository;


import com.fasterxml.jackson.databind.introspect.AnnotationCollector;
import org.codeforpizza.registrationservice.models.entitys.Performer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformerRepository extends JpaRepository<Performer, Long> {


    Performer findByFirstName(String firstName);
}
