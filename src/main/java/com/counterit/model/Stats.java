package com.counterit.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tb_stats")
@Getter @Setter
public class Stats {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne
    @JoinColumn(name = "std_idx")
    private Champ stdChamp;

    @ManyToOne
    @JoinColumn(name = "vs_idx")
    private Champ vsChamp;

    private Double delta;
    private String ver;
    private Integer tier;
}
