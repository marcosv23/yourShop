package yourShop.utility;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import domain.entity.Item;
import domain.entity.FreightCalculator;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FreightCalculatorTestUtilsTest {
    
    @Test
    @DisplayName("Deve calcular o frete mínimo")
    void shouldCalcMinimalShipping() {
        var distanceKm = new BigDecimal("1000");
        var cameraHeight = new BigDecimal("15");
        var cameraWidth = new BigDecimal("20");
        var cameraDepth = new BigDecimal("10");
        var cameraWeight = new BigDecimal("1");
        var camera = new Item(1, new BigDecimal("100.00"), "item3", cameraHeight, cameraWidth, cameraDepth, cameraWeight);
        Assertions.assertEquals(new BigDecimal("10.00"), FreightCalculator.calculate(camera, distanceKm));
    }

    @Test
    @DisplayName("Deve calcular o frete normal")
    void shouldCalcNormalShipping() {
        var distanceKm = new BigDecimal("1000");
        var guitarHeight = new BigDecimal("100");
        var guitarWidth = new BigDecimal("30");
        var guitarDepth = new BigDecimal("10");
        var guitarWeight = new BigDecimal("3");
        var guitar = new Item(1, new BigDecimal("100.00"), "item3", guitarHeight, guitarWidth, guitarDepth, guitarWeight);
        Assertions.assertEquals(new BigDecimal("30.00"), FreightCalculator.calculate(guitar, distanceKm));
    }

}
