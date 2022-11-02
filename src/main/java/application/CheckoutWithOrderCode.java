package application;

import application.input.checkout.CheckoutInputWithCodeDTO;
import domain.entity.Order;
import domain.entity.OrderCode;
import domain.repository.ItemRepository;
import domain.repository.OrderRepository;
import exceptions.ForbiddenActionException;
import java.time.LocalDateTime;

public class CheckoutWithOrderCode {
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    public CheckoutWithOrderCode(ItemRepository itemRepository, OrderRepository orderRepository) {
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
    }

    public void execute(CheckoutInputWithCodeDTO dto){
        var order = new Order(dto.cpf());
        var orderCode = dto.orderCode();
        if(!isValidOrderCode(dto.orderCode())) throw new ForbiddenActionException("This order " +
                "code is invalid");
        dto.itemInputs().forEach(i->{
            var item =itemRepository.getItem(i.itemId());
            order.addItem(item);
            order.setCode(orderCode);
        });
        orderRepository.save(order);
    }

    private boolean isValidOrderCode(String supposedCode){
        var count = orderRepository.count();
        var thisYear = String.valueOf(LocalDateTime.now().getYear());
        var orderCode = OrderCode.generate(Integer.parseInt(thisYear),count.toString());
        return supposedCode.equals(orderCode);
    }
}
