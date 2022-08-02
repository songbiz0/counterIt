package com.counterit.repository;

import com.counterit.model.StatsDTO;
import com.counterit.model.StatsEntity;
import com.counterit.model.StatsInterface;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StatsRepository extends JpaRepository<StatsEntity, Long> {

    @Query(value = "SELECT s.gsum, s.davg, c.krnm " +
            "FROM (SELECT std_idx, vs_idx, SUM(games) AS gsum, round(AVG(delta), 2) AS davg " +
            "FROM tb_stats s " +
            "WHERE std_idx = :#{#dto.std_idx} " +
            "AND tier IN :#{#dto.tiers} " +
            "AND lane = :#{#dto.lane} " +
            "GROUP BY vs_idx " +
            "HAVING gsum >= 1000) s " +
            "INNER JOIN tb_champ c ON s.vs_idx = c.idx " +
            "ORDER BY s.davg",
            nativeQuery = true)
    List<StatsInterface> getStats(@Param("dto")StatsDTO dto);
}
