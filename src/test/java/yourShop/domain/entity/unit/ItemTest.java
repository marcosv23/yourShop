package yourShop.domain.entity.unit;

import domain.entity.item.Item;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {
    private static Item item1;


    @BeforeAll
    public static void initTests() {
        var height = new BigDecimal("20");
        var width = new BigDecimal("15");
        var depth = new BigDecimal("10");
        var weight = new BigDecimal("1");
        item1 = new Item(3, new BigDecimal("10"), "Strawberry Cake", height, width, depth, weight);
    }

    @Test
    @DisplayName("Deve calcular o valor total de um item de acordo com a quantidade")
    void shouldCalcTotalPrice() {
        assertEquals(new BigDecimal("30.00"), item1.calcTotalPrice());
    }

    @Test
    @DisplayName("Deve calcular o volume corretamente")
    void shouldCalcVolume() {
        assertEquals(new BigDecimal("0.003"), item1.getVolume());
    }

    @Test
    @DisplayName("Deve calcular a densidade corretamente")
    void shouldCalcDensity() {
        assertEquals(new BigDecimal("333.3"), item1.getDensity());
    }


}
