package org.codeforpizza.productionservice.repository;

import org.codeforpizza.productionservice.modell.Act;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActRepository extends JpaRepository<Act, Long> {
}
