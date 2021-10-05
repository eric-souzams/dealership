package com.project.Dealership.model.enums;

import lombok.Getter;

@Getter
public enum Profile {

    SALESMAN("Salesman"),
    MANAGER("Manager"),
    ADMIN("Admin");

    private final String description;

    Profile(String description) {
        this.description = description;
    }

}
