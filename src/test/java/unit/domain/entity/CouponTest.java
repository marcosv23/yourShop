package unit.domain.entity;


import domain.entity.Coupon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utility.DateUtils;

import java.text.ParseException;


import static org.junit.jupiter.api.Assertions.*;

class CouponTest {

    @Test
    void shouldAllowValidCoupon() throws ParseException {
        Assertions.assertTrue(Coupon.isValidCoupon(Coupon.GET15OFF, DateUtils.getInstantFromString("2022-02-21T00:00:00")));
    }

    @Test
    void shouldNotAllowExpiredCoupon() throws ParseException {
        assertFalse(Coupon.isValidCoupon(Coupon.GET30OFF, DateUtils.getInstantFromString("2022-02-28T00:10:01")));
    }
}
