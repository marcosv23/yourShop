package yourShop.application.integration;

import application.Checkout;
import application.GetOrdersByCpf;
import application.input.checkout.CheckoutInputDTO;
import application.input.checkout.ItemInputDTO;
import domain.entity.Item;
import domain.entity.Order;
import domain.repository.ItemRepository;
import domain.repository.OrderRepository;
import domain.repository.impl.ItemRepositoryInMemory;
import domain.repository.impl.OrderRepositoryInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckoutTest {
    private static OrderRepository orderRepository = new OrderRepositoryInMemory();
    private  static  ItemRepository itemRepository = new ItemRepositoryInMemory();
    private  UUID ITEM_ID_1;
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
        ITEM_ID_1 = itemRepository.save(item1);
        ITEM_ID_2 = itemRepository.save(item2);
        ITEM_ID_3 = itemRepository.save(item3);
        var order = new Order("01234567890");
        order.addItem(item1);
        orderRepository.save(order);
    }

    @Test
    @DisplayName("Deve realizar um pedido")
    void shouldDoAnOrder(){
    var item1 = new ItemInputDTO(ITEM_ID_1.toString(),1, null);
    var item2 = new ItemInputDTO(ITEM_ID_2.toString(),1, null);
    var itemInputs= List.of(item1,item2);
    var year = LocalDateTime.now().getYear();
    var input  = new CheckoutInputDTO(itemInputs,CPF_INPUT, LocalDateTime.now().getYear());
    var checkout = new Checkout(itemRepository, orderRepository);
    checkout.execute(input);
    var orders = new GetOrdersByCpf(orderRepository,input.cpf()).execute();
    assertEquals(5,orders.size());
    assertEquals(String.valueOf(year).concat("00000002"),orders.get(1).code());
    }

}