package unit.application;

import application.PlaceOrder;
import application.input.InputPlaceOrder;
import domain.entity.OrderItem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PlaceOrderTest {
    private static PlaceOrder placeOrder;

    @BeforeAll
    public static void initTests() {
        placeOrder = new PlaceOrder();
    }

    @Test
    void shouldGenerateOrderCode() {
        assertEquals(12, placeOrder.generatePlaceOrderCode().length());
    }

    @Test
    void shouldMakeAnOrder(){
        var height = new BigDecimal("20");
        var width = new BigDecimal("15");
        var depth = new BigDecimal("10");
        var weight = new BigDecimal("1");
        var item = new OrderItem(3, new BigDecimal(10), "Strawberry Cake", height, width, depth, weight);
        var input = InputPlaceOrder.builder()
                .cpf("645.216.850-99")
                .items(List.of(item))
                .build();
    }
}
