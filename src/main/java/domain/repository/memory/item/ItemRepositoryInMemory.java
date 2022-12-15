package domain.repository.memory.item;

import domain.entity.item.Item;
import domain.repository.ItemRepository;
import application.exceptions.NotFoundException;
import infra.memory.ProductMemoryModel;

import java.util.*;

public class ItemRepositoryInMemory implements ItemRepository {

    private final List<ProductMemoryModel> items = new ArrayList<>();

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


    private Item toItem(ProductMemoryModel productMemoryModel){
        return   Item.builder()
                .height(productMemoryModel.getHeight())
                .depth(productMemoryModel.getDepth())
                .weight(productMemoryModel.getWeight())
                .width(productMemoryModel.getWidth())
                .unitPrice(productMemoryModel.getPrice())
                .build();
    }

    private ProductMemoryModel toItemModel(Item item){
        return  ProductMemoryModel.builder()
                .id(item.getId())
                .depth(item.getDepth())
                .price(item.getUnitPrice())
                .height(item.getHeight())
                .weight(item.getWeight())
                .width(item.getWidth())
                .build();
    }
}
