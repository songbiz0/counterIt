package com.counterit.model;

import lombok.Getter;

import java.util.List;

@Getter
public class ConfigDTO {
    private List<String> top;
    private List<String> jungle;
    private List<String> middle;
    private List<String> bottom;
    private List<String> support;
}
