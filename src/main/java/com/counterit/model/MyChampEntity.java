package com.counterit.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tb_mychamp")
@Getter @Setter
public class MyChampEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne
    @JoinColumn(name = "user_idx")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "champ_idx")
    private ChampEntity champEntity;

    private String lane;
}
