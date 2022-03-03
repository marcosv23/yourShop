package unit.utility;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import domain.entity.OrderItem;
import domain.entity.FreightCalculator;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FreightCalculatorUtilsTest {

    private static OrderItem item1;
    private static OrderItem item2;

    @Test
    public void shouldCalcMinimalShipping() {
        var distanceKm = new BigDecimal("1000");
        Assertions.assertEquals(new BigDecimal("10.00"), FreightCalculator.calculate(item1, distanceKm));
    }

    @Test
    public void shouldCalcNormalShipping() {
        var distanceKm = new BigDecimal("1000");
        assertEquals(new BigDecimal("30.00"), FreightCalculator.calculate(item2, distanceKm));
    }

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

}
