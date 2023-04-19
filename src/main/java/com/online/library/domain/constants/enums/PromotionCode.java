package com.online.library.domain.constants.enums;

/**
 * Created by Shajahan Shaik on 04/18/2023.
 */

public enum PromotionCode {
    PROMO10(10L, "PROMO10");

    private final String value;
    private Long id;

    PromotionCode(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public Long getId() {
        return id;
    }

    public static Long getCodeByValue(String type) {
        for (PromotionCode source : values()) {
            if (source.getValue().equalsIgnoreCase(type)) {
                return source.getId();
            }
        }
        return null;
    }
}

