package domain.repository;

import domain.entity.order.Order;

import java.util.List;

public interface OrderRepository {
    List<Order > getByCpf(String cpf);
    long count();
    Integer countAsInt();
    void save(Order order);
}
