package com.counterit.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tb_stats")
@Getter @Setter
public class StatsEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne
    @JoinColumn(name = "std_idx")
    private ChampEntity stdChampEntity;

    @ManyToOne
    @JoinColumn(name = "vs_idx")
    private ChampEntity vsChampEntity;

    private Double delta;
    private String ver;
    private String tier;
    private String lane;
    private int games;
}
