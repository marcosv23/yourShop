package yourShop.application;

import application.SimulateFreight;
import application.input.freight.FreightInput;
import domain.entity.item.Item;
import domain.repository.ItemRepository;
import domain.repository.memory.item.ItemRepositoryInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimulateFreightTest {
    private UUID ITEM_ID_1;
    private  UUID ITEM_ID_2;
    private  UUID ITEM_ID_3;
    private ItemRepository ItemRepository = new ItemRepositoryInMemory();

    @BeforeEach
    void insertItemsOnDatabase(){
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
    @DisplayName("Deve simular o frete")
    void shouldSimulateFreight(){
        var inputFreight = new FreightInput(ITEM_ID_1.toString(),"2000");
        var simulateFreight = new SimulateFreight(ItemRepository);
        var freight = simulateFreight.execute(inputFreight);
        assertEquals(new BigDecimal("19.80"),freight);
    }
}
