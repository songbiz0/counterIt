package com.counterit.repository;

import com.counterit.model.CrawlingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CrawlingRepository extends JpaRepository<CrawlingEntity, Long> {

    Optional<CrawlingEntity> findByVer(String ver);

}
