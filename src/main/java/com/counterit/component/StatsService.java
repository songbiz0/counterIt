package com.counterit.component;

import com.counterit.model.ChampEntity;
import com.counterit.model.StatsDTO;
import com.counterit.repository.ChampRepository;
import com.counterit.repository.StatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

    @Autowired
    StatsRepository statsRepository;
    @Autowired
    ChampRepository champRepository;

    public StatsDTO setStdIdx(StatsDTO dto) {
        ChampEntity champ = champRepository.findByKrnm(dto.getChampname());
        dto.setStd_idx(champ.getIdx());
        return dto;
    }
}
