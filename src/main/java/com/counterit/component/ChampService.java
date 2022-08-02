package com.counterit.component;

import com.counterit.model.ChampEntity;
import com.counterit.repository.ChampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChampService {

    @Autowired
    ChampRepository champRepository;

    public void getChampList() {
        List<ChampEntity> champEntityList = champRepository.findAll();
        for(ChampEntity champEntity : champEntityList) {
            System.out.println(champEntity.getKrnm() + " : " + champEntity.getEnnm());
        }
    }
}
