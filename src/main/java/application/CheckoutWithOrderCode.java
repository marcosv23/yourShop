package application;

import application.input.checkout.CheckoutInputWithCodeDTO;
import domain.entity.order.Order;
import domain.entity.order.OrderCode;
import domain.repository.ItemRepository;
import domain.repository.OrderRepository;
import application.exceptions.ForbiddenActionException;
import java.time.LocalDateTime;

public class CheckoutWithOrderCode {
    private final ItemRepository ItemRepository;
    private final OrderRepository OrderRepository;

    public CheckoutWithOrderCode(ItemRepository ItemRepository, OrderRepository OrderRepository) {
        this.ItemRepository = ItemRepository;
        this.OrderRepository = OrderRepository;
    }

    public void execute(CheckoutInputWithCodeDTO dto){
        var order = new Order(dto.cpf());
        var orderCode = dto.orderCode();
        if(!isValidOrderCode(dto.orderCode())) throw new ForbiddenActionException("This order " +
                "code is invalid");
        dto.itemInputs().forEach(i->{
            var item = ItemRepository.getItem(i.itemId());
            order.addItem(item);
            order.setCode(orderCode);
        });
        OrderRepository.save(order);
    }

    private boolean isValidOrderCode(String supposedCode){
        var count = OrderRepository.count();
        var thisYear = String.valueOf(LocalDateTime.now().getYear());
        var orderCode = OrderCode.generate(Integer.parseInt(thisYear),String.valueOf(count));
        return supposedCode.equals(orderCode);
    }
}
