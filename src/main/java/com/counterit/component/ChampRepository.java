package com.counterit.component;

import com.counterit.model.ChampEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampRepository extends JpaRepository<ChampEntity, Long> {
    ChampEntity findByEnnm(String ennm);
}