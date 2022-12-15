package infra.memory;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Builder
public class CouponMemoryModel {
    private  BigDecimal percentage;
    private  String name;
    private  Instant expirationDate;
}
