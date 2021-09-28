package com.project.Dealership.model.enums;

import lombok.Getter;

@Getter
public enum Situation {

    FOR_SALE("For sale"),
    SOLD("Sold");

    private final String description;

    Situation(String description) {
        this.description = description;
    }
}
