package com.online.library.domain.constants.enums;

/**
 * Created by Shajahan Shaik on 04/18/2023.
 */

public enum ServiceType {
    CREATE(1L, "CREATE"),
    UPDATE(2L, "UPDATE"),
    DELETE(3L, "DELETE"),
    CHECKOUT(4L, "CHECKOUT");

    private final String value;
    private Long id;

    ServiceType(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public Long getId() {
        return id;
    }
}

