package service;


import org.junit.jupiter.api.Test;
import utility.DateUtils;
import java.text.ParseException;


import static org.junit.jupiter.api.Assertions.*;

public class CouponTest {

    @Test
    public void shouldAllowValidCoupon() throws ParseException {
        assertTrue(Coupon.isValidCoupon(Coupon.GET15OFF, DateUtils.getInstantFromString("2022-02-21T00:00:00")));
    }

    @Test
    public void shouldNotAllowCouponExpired() throws ParseException {
        assertFalse(Coupon.isValidCoupon(Coupon.GET30OFF, DateUtils.getInstantFromString("2022-25-10T11:00:00")));
    }
}
