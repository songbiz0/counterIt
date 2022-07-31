package com.counterit.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tb_mychamp")
@Getter @Setter
public class Crawling {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String ver;
    private String stat;
}
