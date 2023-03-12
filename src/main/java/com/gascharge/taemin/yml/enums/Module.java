package com.gascharge.taemin.yml.enums;

import lombok.Getter;

import java.util.Arrays;

public enum Module {
    MODULE(0), DOMAIN(1), SERVICE(2), APP(3);

    @Getter
    private int order;

    Module(int order) {
        this.order = order;
    }

    public static Module from(String name) {
        return Arrays.stream(Module.values())
                .filter(m -> m.name().equalsIgnoreCase(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(name + " 에 해당하는 Module enum 을 찾을 수 없습니다."));
    }
}
