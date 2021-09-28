package com.project.Dealership.model.enums;

import lombok.Getter;

@Getter
public enum State {

    NEW("New"),
    USED("Used"),
    POOR_CONDITION("Poor condition");

    private final String description;

    State(String description) {
        this.description = description;
    }
}
