package com.counterit.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tb_champ")
@Getter @Setter
public class Champ {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String krnm;
    private String ennm;
}
