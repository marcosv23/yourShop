package domain.repository;

import domain.entity.Item;

import java.util.UUID;

public interface ItemRepository {

    Item getItem(String id);

    UUID save(Item item);

}
