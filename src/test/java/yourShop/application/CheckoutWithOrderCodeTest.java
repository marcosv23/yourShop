package yourShop.application;


import application.CheckoutWithOrderCode;
import application.input.checkout.CheckoutInputWithCodeDTO;
import application.input.checkout.ItemInputDTO;
import domain.entity.item.Item;
import domain.entity.order.Order;
import domain.repository.ItemRepository;
import domain.repository.OrderRepository;
import domain.repository.memory.item.ItemRepositoryInMemory;
import domain.repository.memory.order.OrderRepositoryInMemory;
import application.exceptions.ForbiddenActionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CheckoutWithOrderCodeTest {
        private static final OrderRepository I_ORDER_REPOSITORY = new OrderRepositoryInMemory();
        private  static final ItemRepository I_ITEM_REPOSITORY = new ItemRepositoryInMemory();
        private UUID ITEM_ID_1;
        private  UUID ITEM_ID_2;
        private  UUID ITEM_ID_3;
        private static final String CPF_INPUT= "01234567890";

        @BeforeEach
        void insertItemsOnDatabase(){
            var height = new BigDecimal("20");
            var width = new BigDecimal("15");
            var depth = new BigDecimal("10");
            var weight = new BigDecimal("1");
            var item1 = new Item(1, new BigDecimal("100.00"), "item1", height, width, depth, weight);
            var item2 = new Item(1, new BigDecimal("101.00"), "item2", height, width, depth, weight);
            var item3 = new Item(1, new BigDecimal("102.00"), "item3", height, width, depth, weight);
            ITEM_ID_1 = I_ITEM_REPOSITORY.save(item1);
            ITEM_ID_2 = I_ITEM_REPOSITORY.save(item2);
            ITEM_ID_3 = I_ITEM_REPOSITORY.save(item3);
            var order = new Order("01234567890");
            order.addItem(item1);
            I_ORDER_REPOSITORY.save(order);
        }

    @Test
    @DisplayName("Deve criar um novo pedido com base no código")
    void shouldCheckoutBasedOnOrderCode(){
        var checkout = new CheckoutWithOrderCode(I_ITEM_REPOSITORY, I_ORDER_REPOSITORY);
        var item1 = new ItemInputDTO(ITEM_ID_1.toString(),1, null);
        var item2 = new ItemInputDTO(ITEM_ID_2.toString(),1, null);
        var itemInputs= List.of(item1,item2);
        var input  = new CheckoutInputWithCodeDTO(itemInputs,CPF_INPUT, "202200000002");
        checkout.execute(input);
        var orderCount= I_ORDER_REPOSITORY.count();
        assertEquals(2,orderCount);
    }

    @Test
    @DisplayName("Não Deve criar um novo pedido com base no código quando o código for inválido")
    void shouldNotCheckoutWithInvalidOCode(){
        var checkout = new CheckoutWithOrderCode(I_ITEM_REPOSITORY, I_ORDER_REPOSITORY);
        var item1 = new ItemInputDTO(ITEM_ID_1.toString(),1, null);
        var item2 = new ItemInputDTO(ITEM_ID_2.toString(),1, null);
        var itemInputs= List.of(item1,item2);
        var input  = new CheckoutInputWithCodeDTO(itemInputs,CPF_INPUT, "300000000002");
        Exception exception =assertThrows(ForbiddenActionException.class, ()->checkout.execute(input));
        assertTrue(exception.getMessage().equals("This order code is invalid"));
    }
}
