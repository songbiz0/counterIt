package com.counterit.model;

public interface StatsInterface {
    int getGsum();
    double getDavg();
    String getKrnm();

    default StatsVO getStats() {
        StatsVO vo = new StatsVO();
        vo.setGames(getGsum());
        vo.setDelta(getDavg());
        vo.setVschampname(getKrnm());
        return vo;
    }
}