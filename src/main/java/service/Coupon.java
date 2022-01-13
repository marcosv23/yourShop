package service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.math.BigDecimal;


@Getter
@AllArgsConstructor
public enum Coupon {
    GET10OFF(new BigDecimal("0.10"), "10% COUPON"),
    GET15OFF(new BigDecimal("0.15"), "15% COUPON"),
    GET30OFF(new BigDecimal("0.30"), "30% COUPON");
    private final BigDecimal percentage;
    private final String description;
}
