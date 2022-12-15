package domain.repository.db.mysql.jpa.item;

import domain.entity.item.Item;
import infra.database.ProductDBModel;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Service
public class ItemRepository implements domain.repository.ItemRepository {

    @PersistenceContext
    EntityManager entityManager;
    
    @Override
    public Item getItem(String id) {
        var query = entityManager
                .createQuery("SELECT p FROM ProductDBModel p WHERE p.id = :  id", ProductDBModel.class);
        query.getResultList().stream().map(dbResult-> Item
                .builder()
                .id(dbResult.getId())
                .price(dbResult.getPrice())
                .height(dbResult.getHeight())
                .description(dbResult.getDescription())
                .weight(dbResult.getWeight())
                // TODO REMOVE QUANTITY ANDO OTHERS ATTRIBUTES
                .build());
        return null;
    }

    @Override
    public UUID save(Item item) {
        return null;
    }
}
