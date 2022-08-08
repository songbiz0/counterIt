package com.counterit.model;

import lombok.Getter;

import java.util.List;

@Getter
public class ConfigDTO {
    private List<Long> top;
    private List<Long> jungle;
    private List<Long> middle;
    private List<Long> bottom;
    private List<Long> support;
}
