package infra.database;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Builder
public class CouponDBModel {
    private  BigDecimal percentage;
    private  String name;
    private  Instant expirationDate;
}
