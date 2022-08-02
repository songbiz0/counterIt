package com.counterit.repository;

import com.counterit.model.StatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatsRepository extends JpaRepository<StatsEntity, Long> {

}
