package org.codeforpizza.productionservice.repository;

import org.codeforpizza.productionservice.modell.entitys.AwanChats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AwanRespository extends JpaRepository<AwanChats, Long> {
}
