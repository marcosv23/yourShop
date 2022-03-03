package domain.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FreightCalculator {
    private static final BigDecimal FACTOR_TO_DIVIDE_DENSITY = new BigDecimal("100");
    private static final BigDecimal MINIMAL_SHIPPING_VALUE = new BigDecimal("10.00");

    private FreightCalculator() {
    }

    public static BigDecimal calculate(OrderItem item, BigDecimal distanceKm) {
        var normalValue = freightCalcFormula(item, distanceKm);
        return receivesMinimalFreight(normalValue) ? MINIMAL_SHIPPING_VALUE : normalValue;
    }

    private static BigDecimal freightCalcFormula(OrderItem item, BigDecimal distanceKm) {
        return distanceKm.multiply(item.getVolume()).multiply(divideDensityByFactor(item.getDensity()))
                .setScale(2, RoundingMode.DOWN);
    }

    private static boolean receivesMinimalFreight(BigDecimal supposedValue) {
        return supposedValue.compareTo(MINIMAL_SHIPPING_VALUE) < 0;
    }

    private static BigDecimal divideDensityByFactor(BigDecimal density) {
        return density.divide(FACTOR_TO_DIVIDE_DENSITY, 1, RoundingMode.DOWN);
    }
}
