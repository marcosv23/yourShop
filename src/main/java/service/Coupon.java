package service;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;


@Getter
@AllArgsConstructor
public enum Coupon {
    GET10OFF(new BigDecimal("0.10"), "10% COUPON", getInstantFromString("2022-02-25T00:10:00")),
    GET15OFF(new BigDecimal("0.15"), "15% COUPON", getInstantFromString("2022-02-21T01:19:00")),
    GET30OFF(new BigDecimal("0.30"), "30% COUPON", getInstantFromString("2022-02-28T00:10:00"));
    private final BigDecimal percentage;
    private final String description;
    private final Instant expirationDate;

    private static final Map<BigDecimal, Coupon> index = Maps.newHashMapWithExpectedSize(Coupon.values().length);

    static {
        for (Coupon coupon : Coupon.values()) {
            index.put(coupon.getPercentage(), coupon);
        }
    }

    public static boolean isValidCoupon(Coupon coupon, Instant date) {
        var result = index.get(coupon.getPercentage());
        return date.compareTo(result.getExpirationDate()) <= 0;
    }

    public static Instant getInstantFromString(String strDate) {
        return LocalDateTime.parse(strDate).toInstant(ZoneOffset.UTC);
    }

    public static BigDecimal calculateDiscount(BigDecimal price, Coupon coupon) {
        return price.multiply(coupon.getPercentage());
    }
}
