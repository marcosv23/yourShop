package domain.repository.db.mysql.jpa.order;

import domain.entity.order.Order;
import domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("orderRepositoryDB")
public class OrderRepositoryDB implements OrderRepository {

    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<Order> getByCpf(String cpf) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Integer countAsInt() {
        return null;
    }

    @Override
    public void save(Order order) {

    }
}
