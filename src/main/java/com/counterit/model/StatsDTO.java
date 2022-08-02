package com.counterit.model;

import lombok.Data;

import java.util.List;

@Data
public class StatsDTO {
//    private boolean silver;
//    private boolean gold_plus;
    private List<String> tiers;
    private String lane;
    private String champname;
    private Long std_idx;
}
