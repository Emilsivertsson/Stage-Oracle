package org.codeforpizza.productionservice.repository;

import org.codeforpizza.productionservice.modell.Manifest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManifestRepository extends JpaRepository<Manifest, Long> {
}
