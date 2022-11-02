package application;

import application.output.OrdersOutput;
import domain.repository.OrderRepository;
import exceptions.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class GetOrdersByCpf {

    private final OrderRepository orderRepository;
    private final String cpf;

    public List<OrdersOutput> execute(){
        var orders = orderRepository.getByCpf(cpf);
        if(orders.isEmpty()) throw  new NotFoundException("Does not exist any order for this cpf");
        return orders.stream()
                .map(
                o-> new OrdersOutput(
                        o.getCpf(),o.getCode(),
                        o.getDescription(),o.getTotalPrice())
        ).collect(Collectors.toList());
    }


    public GetOrdersByCpf(OrderRepository orderRepository, String cpf) {
        this.orderRepository = orderRepository;
        this.cpf = cpf;
    }
}
