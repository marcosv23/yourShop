package application;

import application.output.OrdersOutput;
import domain.repository.OrderRepository;
import application.exceptions.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class GetOrdersByCpf {

    private final OrderRepository OrderRepository;
    private final String cpf;

    public List<OrdersOutput> execute(){
        var orders = OrderRepository.getByCpf(cpf);
        if(orders.isEmpty()) throw  new NotFoundException("Does not exist any order for this cpf");
        return orders.stream()
                .map(
                o-> new OrdersOutput(
                        o.getCpf(),o.getCode(),
                        o.getDescription(),o.getPrice())
        ).collect(Collectors.toList());
    }


    public GetOrdersByCpf(OrderRepository OrderRepository, String cpf) {
        this.OrderRepository = OrderRepository;
        this.cpf = cpf;
    }
}
