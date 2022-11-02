package yourShop.domain.entity.unit;


import domain.entity.Coupon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;
class CouponTest {

    @Test
    @DisplayName("Deve permitir adicionar um cupom não expirado")
    void shouldAllowValidCoupon() {
        var now =  Instant.now();
        var tomorrowDate = now.plus(1, ChronoUnit.DAYS);
        var coupon = Coupon.builder()
                        .name("COUPON BF FANTASTICA")
                        .percentage(new BigDecimal("15"))
                        .expirationDate(tomorrowDate)
                        .build();
        Assertions.assertTrue(coupon.isValidCoupon());
    }

    @Test
    @DisplayName("Não deve permitir adicionar um cupom expirado")
    void shouldNotAllowExpiredCoupon() {
        var now =  Instant.now();
        var tomorrowDate = now.minus(1, ChronoUnit.DAYS);
        var coupon = Coupon.builder()
                .name("COUPON BF FANTASTICA")
                .percentage(new BigDecimal("15"))
                .expirationDate(tomorrowDate)
                .build();
        assertFalse(coupon.isValidCoupon());
    }
}
