package com.counterit.model;

import lombok.Data;

@Data
public class StatsVO {
    private String krnm;
    private Double delta;
    private int games;

    private String vsennm;
}
