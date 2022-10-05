package domain.entity;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Map;

import static utility.DateUtils.getInstantFromString;


@Getter
@Builder
@AllArgsConstructor
public class Coupon {
    private final BigDecimal percentage;
    private final String description;
    private final Instant expirationDate;
    private static final BigDecimal PERCENTAGE_FACTOR_CONVERSION_FOR_DECIMAL =new BigDecimal(100);
    
    public BigDecimal getDecimalDiscount(){
        return percentage.divide(PERCENTAGE_FACTOR_CONVERSION_FOR_DECIMAL).setScale(2, RoundingMode.FLOOR);
    }

    public  boolean isValidCoupon() {
        var now = Instant.now();
        return this.expirationDate.compareTo(now) > 0;
    }
}
