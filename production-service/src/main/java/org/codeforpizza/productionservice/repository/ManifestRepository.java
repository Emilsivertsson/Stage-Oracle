package org.codeforpizza.productionservice.repository;

import org.codeforpizza.productionservice.modell.entitys.Manifest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManifestRepository extends JpaRepository<Manifest, Long> {
    List<Manifest> findAllByProductionId(Long productionId);



}
