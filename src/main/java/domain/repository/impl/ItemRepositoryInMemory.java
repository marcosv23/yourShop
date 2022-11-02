package domain.repository.impl;

import domain.entity.Item;
import domain.repository.ItemRepository;
import exceptions.NotFoundException;
import infra.database.ProductDBModel;

import java.util.*;

public class ItemRepositoryInMemory implements ItemRepository {

    private final List<ProductDBModel> items = new ArrayList<>();

    @Override
    public Item getItem(String id) {
        var item = items.stream()
                .filter(i-> i.getId().equals(id))
                .findFirst()
                .orElseThrow(()->  new NotFoundException("This product does not exists"));
        return toItem(item);
    }

    @Override
    public UUID save(Item item) {
        var id = UUID.randomUUID();
        item.setId(id.toString());
        var toSave = toItemModel(item);
        items.add(toSave);
        return id;
    }


    private Item toItem(ProductDBModel productDBModel){
        return   Item.builder()
                .height(productDBModel.getHeight())
                .depth(productDBModel.getDepth())
                .weight(productDBModel.getWeight())
                .width(productDBModel.getWidth())
                .unitPrice(productDBModel.getUnitPrice())
                .build();
    }

    private ProductDBModel toItemModel(Item item){
        return  ProductDBModel.builder()
                .id(item.getId())
                .depth(item.getDepth())
                .unitPrice(item.getUnitPrice())
                .height(item.getHeight())
                .weight(item.getWeight())
                .width(item.getWidth())
                .build();
    }
}
