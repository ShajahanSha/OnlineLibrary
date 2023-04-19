package com.online.library.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Amount extends StoreModel {

    @Getter(AccessLevel.NONE)
    private BigDecimal value;

    private String currency;

    /**
     * Default Constructor
     */
    public Amount() {
    }

    /**
     * Parameterized Constructor
     */
    public Amount(BigDecimal value, String currency) {
        this.currency = currency;
        this.value = value;
    }

    public BigDecimal getValue() {
        if(value != null){
            value = value.setScale(2, RoundingMode.HALF_UP);
        }
        return value;
    }
}
