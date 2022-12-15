package application;

import application.input.freight.FreightInput;
import domain.entity.FreightCalculator;
import domain.repository.ItemRepository;

import java.math.BigDecimal;

public class SimulateFreight {
    private final ItemRepository ItemRepository;

    public SimulateFreight(ItemRepository ItemRepository) {
        this.ItemRepository = ItemRepository;
    }

    public BigDecimal execute(FreightInput input){
        var item = ItemRepository.getItem(input.idItem());
        return FreightCalculator.calculate(item, new BigDecimal(input.distanceKm()));
    }
}
