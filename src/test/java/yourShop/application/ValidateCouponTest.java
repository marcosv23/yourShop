package yourShop.application;

import domain.entity.coupon.Coupon;
import domain.entity.item.Item;
import domain.entity.order.Order;
import domain.repository.ItemRepository;
import domain.repository.OrderRepository;
import domain.repository.memory.item.ItemRepositoryInMemory;
import domain.repository.memory.order.OrderRepositoryInMemory;
import application.exceptions.InvalidCouponException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidateCouponTest {
    private OrderRepository OrderRepository;
    private ItemRepository ItemRepository;
    private UUID ITEM_ID_1;
    private  UUID ITEM_ID_2;
    private  UUID ITEM_ID_3;
    private static final String CPF_INPUT= "01234567890";


    @BeforeEach
    void insertItemsOnDatabase(){
        OrderRepository = new OrderRepositoryInMemory();
        ItemRepository = new ItemRepositoryInMemory();
        var height = new BigDecimal("20");
        var width = new BigDecimal("15");
        var depth = new BigDecimal("10");
        var weight = new BigDecimal("1");
        var item1 = new Item(1, new BigDecimal("100.00"), "item1", height, width, depth, weight);
        var item2 = new Item(1, new BigDecimal("101.00"), "item2", height, width, depth, weight);
        var item3 = new Item(1, new BigDecimal("102.00"), "item3", height, width, depth, weight);
        ITEM_ID_1 = ItemRepository.save(item1);
        ITEM_ID_2 = ItemRepository.save(item2);
        ITEM_ID_3 = ItemRepository.save(item3);
    }

    @Test
    @DisplayName("Deve validar um cupom")
    void shouldValidateCoupon(){
        var height = new BigDecimal("20");
        var width = new BigDecimal("15");
        var depth = new BigDecimal("10");
        var weight = new BigDecimal("1");
        var item1 = new Item(1, new BigDecimal("100.00"), "item1", height, width, depth, weight);
       var order = new Order(CPF_INPUT);
       order.addItem(item1);
       var coupon = Coupon
               .builder()
               .name("WORLD CUP QATAR 5 % OFF")
               .expirationDate(Instant.now().minus(1, ChronoUnit.DAYS))
               .build();
       Exception exception = assertThrows(InvalidCouponException.class, ()-> order.addCoupon(coupon));
       assertEquals("Coupon is invalid or expired",exception.getMessage());
    }
}
