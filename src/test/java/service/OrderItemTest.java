package service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utility.FreightCalculatorUtils;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderItemTest {

    private static OrderItem item1;
    private static OrderItem item2;

    @BeforeAll
    public static void initTests() {
        var height = new BigDecimal("20");
        var width = new BigDecimal("15");
        var depth = new BigDecimal("10");
        var weight = new BigDecimal("1");

        var height2 = new BigDecimal("100");
        var width2 = new BigDecimal("30");
        var depth2 = new BigDecimal("10");
        var weight2 = new BigDecimal("3");
        item1 = new OrderItem(3, 10, "Strawberry Cake", height, width, depth, weight);
        item2 = new OrderItem(1, 100.00, "item3", height2, width2, depth2, weight2);
    }

    @Test
    public void shouldCalcOrderItemTotalPrice() {
        assertEquals(new BigDecimal(30), item1.getPrice());
    }

    @Test
    public void shouldCalcOrderItemVolume() {
        assertEquals(new BigDecimal("0.003"), item1.getVolume());
    }

    @Test
    public void shouldCalcOrderItemDensity() {
        assertEquals(new BigDecimal("333.3"), item1.getDensity());
    }

    @Test
    public void shouldCalcMinimalShipping() {
        var distanceKm = new BigDecimal("1000");
        assertEquals(new BigDecimal("10.00"), FreightCalculatorUtils.calculate(item1, distanceKm));
    }

    @Test
    public void shouldCalcNormalShipping() {
        var distanceKm = new BigDecimal("1000");
        assertEquals(new BigDecimal("30.00"), FreightCalculatorUtils.calculate(item2, distanceKm));
    }

}
