package domain.repository.memory.order;

import application.exceptions.ForbiddenActionException;
import domain.entity.order.Order;
import domain.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderRepositoryInMemory implements OrderRepository {
    private static final List<Order> orders= new ArrayList<>();

    @Override
    public List<Order> getByCpf(String cpf) {
        return orders.stream().filter(x-> Objects.equals(x.getCpf(), cpf)).toList();
    }

    @Override
    public long count() {
        return orders.size();
    }

    @Override
    public Integer countAsInt() {
        throw new ForbiddenActionException("Method not implemented");
    }

    @Override
    public void save(Order order) {
         orders.add(order);
    }
}
