package application;

import application.input.checkout.CheckoutInputDTO;
import domain.entity.order.Order;
import domain.entity.order.OrderCode;
import domain.repository.ItemRepository;
import domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Checkout {

    private static final String SUCESS_MESSAGE = "Pedido realizado com sucesso";
    private final ItemRepository ItemRepository;
    private final OrderRepository OrderRepository;
    
    public void execute(CheckoutInputDTO dto){
         var order = new Order(dto.cpf());
         var ordersCount = OrderRepository.count();
         var orderCode = OrderCode.generate(dto.year(), String.valueOf(ordersCount));
         dto.itemInputs().forEach(i->{
             var item = ItemRepository.getItem(i.itemId());
             order.addItem(item);
             order.setCode(orderCode);
         });
         OrderRepository.save(order);
    }

}
