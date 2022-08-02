package com.counterit.component;

import com.counterit.model.StatsDTO;
import com.counterit.model.StatsVO;
import com.counterit.repository.ChampRepository;
import com.counterit.repository.StatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
public class StatsService {

    @Autowired
    StatsRepository statsRepository;
    @Autowired
    ChampRepository champRepository;

    public List<StatsVO> getStats(StatsDTO statsDTO) {
        Long idx = champRepository.findByKrnm(statsDTO.getChampname()).getIdx();
        StringBuilder sb = new StringBuilder();
        if(statsDTO.isSilver()) {
            sb.append("'silver'");
        }
        if(statsDTO.isGold_plus()) {
            if(sb.length() != 0) {
                sb.append(", ");
            }
            sb.append("'gold_plus'");
        }

        String sql = "SELECT s.games, s.delta, c.krnm AS vschampname FROM " +
                " (SELECT std_idx, SUM(games) AS games, round(AVG(delta), 2) AS delta " +
                " FROM tb_stats s " +
                " WHERE std_idx = ? " +
                " AND tier IN (?) " +
                " AND lane = ?" +
                " GROUP BY vs_idx " +
                " HAVING games >= 1000) s " +
                "INNER JOIN tb_champ c " +
                "ON s.std_idx = c.idx " +
                "ORDER BY s.delta";
    }
}
