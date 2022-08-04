package com.counterit.model;

import com.counterit.component.ChampService;

public interface StatsInterface {

    int getGsum();
    double getDavg();
    String getKrnm();
    String getVsennm();

    default StatsVO getStats() {
        StatsVO vo = new StatsVO();
        vo.setGames(getGsum());
        vo.setDelta(getDavg());
        vo.setKrnm(getKrnm());
        vo.setVsennm(ChampService.champNameSimplify(getVsennm()));
        return vo;
    }
}