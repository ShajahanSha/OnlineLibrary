package com.online.library.domain.constants.enums;

/**
 * Created by Shajahan Shaik on 04/18/2023.
 */

public enum ClassificationType {
    FICTION(1L, "FICTION"),
    COMIC(2L, "COMIC");

    private final String value;
    private Long id;

    ClassificationType(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public static Long getCodeByValue(String type) {
        for (ClassificationType source : values()) {
            if (source.getValue().equalsIgnoreCase(type)) {
                return source.getId();
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public Long getId() {
        return id;
    }
}

