package domain.repository;

import domain.entity.Order;

import java.util.List;

public interface OrderRepository {
    List<Order > getByCpf(String cpf);
    Integer count();
    void save(Order order);
}
