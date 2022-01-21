package service;

import exceptions.InvalidCouponException;
import exceptions.InvalidCpfException;

import java.math.BigDecimal;
import java.text.ParseException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utility.DateUtils;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    private static Order order;
    private static Order order2;
    private static final BigDecimal DISTANCE_1000_KM = new BigDecimal("1000");
    private static final String VALID_CPF = "404.038.210-29";
    private static final String INVALID_CPF = "404.038.210-27";
    private static final String INVALID_CPF_MESSAGE = "There is not permitted to make an order with invalid CPF";
    private static final String INVALID_COUPON_MESSAGE = "Coupon is invalid or expired";
    private static final String ZONE = "America/Sao_Paulo";

    @Test
    public void shouldCreateEmptyOrderWithValidCPF() {
        var order = new Order(VALID_CPF);
        assertTrue(order.validateCpf(order.getCpf()));
    }

    @Test
    public void shouldCreateThreeItemsOrderWithValidCPF() {
        assertTrue(order.validateCpf(order.getCpf()));
    }


    @Test
    public void shouldCalcOrderPrice() {
        assertEquals(new BigDecimal("400"), order.calcPrice());
    }

    @Test
    public void shouldNotCreateOrderWithInValidCPF() {
        Exception exception = assertThrows(InvalidCpfException.class, () -> {
            new Order(INVALID_CPF, "Music items");
        });
        assertTrue(exception.getMessage().contains(INVALID_CPF_MESSAGE));
    }

    @Test
    public void shouldCalcDiscountFor10OFFCoupon() {
        assertEquals(0, new BigDecimal("360.0").compareTo(order.calcPriceWithDiscount(Coupon.GET10OFF)));
    }

    @Test
    public void shouldCalcDiscountFor15OFFCoupon() {
        assertEquals(0, new BigDecimal("340").compareTo(order.calcPriceWithDiscount(Coupon.GET15OFF)));
    }

    @Test
    public void shouldNotValidExpiredCoupon() {
        Exception exception = assertThrows(InvalidCouponException.class, () -> {
            var date = DateUtils.getInstantFromString("2022-02-25T00:11:00");
            order.addCoupon(Coupon.GET10OFF, date);
        });
        assertTrue(exception.getMessage().contains(INVALID_COUPON_MESSAGE));
    }

    @Test
    public void shouldValidCoupon() throws ParseException {
        var coupon = Coupon.GET10OFF;
        var date = DateUtils.getInstantFromString("2022-02-25T00:00:00");
        order.addCoupon(coupon, date);
        assertTrue(order.validateCoupon(coupon, date));
    }

    @Test
    public void shouldCalcOrderShippingAndReturnMinimalShippingValue() {
        assertEquals(new BigDecimal("30.00"), order.calcFreight(DISTANCE_1000_KM));
    }

    @Test
    public void shouldCalcOrderShippingAndReturnNormalShippingValue() {
        assertEquals(new BigDecimal("10.00"), order2.calcFreight(DISTANCE_1000_KM));
    }

    @BeforeAll
    static void initTests() {
        order = new Order(VALID_CPF, "Music items");
        var height = new BigDecimal("20");
        var width = new BigDecimal("15");
        var depth = new BigDecimal("10");
        var weight = new BigDecimal("1");
        var item1 = new OrderItem(2, 100.0, "item1", height, width, depth, weight);
        var item2 = new OrderItem(1, 100.0, "item2", height, width, depth, weight);
        var item3 = new OrderItem(1, 100.00, "item3", height, width, depth, weight);
        order.addItem(item1);
        order.addItem(item2);
        order.addItem(item3);

        order2 = new Order(VALID_CPF, "Music items");
        var height2 = new BigDecimal("20");
        var width2 = new BigDecimal("15");
        var depth2 = new BigDecimal("10");
        var weight2 = new BigDecimal("1");
        var item4 = new OrderItem(1, 100.0, "Guitar", height2, width2, depth2, weight2);
        order2.addItem(item4);
    }

}
