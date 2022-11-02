package domain.repository.impl;

import domain.entity.Order;
import domain.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderRepositoryInMemory  implements OrderRepository {
    private static final List<Order> orders= new ArrayList<>();

    @Override
    public List<Order> getByCpf(String cpf) {
        return orders.stream().filter(x-> Objects.equals(x.getCpf(), cpf)).toList();
    }

    @Override
    public Integer count() {
        return orders.size();
    }

    @Override
    public void save(Order order) {
         orders.add(order);
    }
}
