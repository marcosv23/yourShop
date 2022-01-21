package utility;

import service.OrderItem;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FreightCalculatorUtils {

    private static final BigDecimal FACTOR_TO_DIVIDE_DENSITY = new BigDecimal("100");
    private static final BigDecimal MINIMAL_SHIPPING_VALUE = new BigDecimal("10.00");

    private FreightCalculatorUtils() {
    }

    public static BigDecimal calculate(OrderItem item, BigDecimal distanceKm) {
        var normalValue = freightCalcFormula(item, distanceKm);
        return receivesMinimalFreight(normalValue) ? MINIMAL_SHIPPING_VALUE : normalValue;
    }

    public static BigDecimal freightCalcFormula(OrderItem item, BigDecimal distanceKm) {
        return distanceKm.multiply(item.getVolume()).multiply(divideDensityByFactor(item.getDensity()))
                .setScale(2, RoundingMode.DOWN);
    }

    public static boolean receivesMinimalFreight(BigDecimal supposedValue) {
        return supposedValue.compareTo(MINIMAL_SHIPPING_VALUE) < 0;
    }

    public static BigDecimal divideDensityByFactor(BigDecimal density) {
        return density.divide(FACTOR_TO_DIVIDE_DENSITY, 1, RoundingMode.DOWN);
    }
}
