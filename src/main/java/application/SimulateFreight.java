package application;

import application.input.freight.FreightInput;
import domain.entity.FreightCalculator;
import domain.repository.ItemRepository;

import java.math.BigDecimal;

public class SimulateFreight {
    private final ItemRepository itemRepository;

    public SimulateFreight(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public BigDecimal execute(FreightInput input){
        var item = itemRepository.getItem(input.idItem());
        return FreightCalculator.calculate(item, new BigDecimal(input.distanceKm()));
    }
}
