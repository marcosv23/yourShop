package application;

import application.input.checkout.CheckoutInputDTO;
import com.google.common.base.Strings;
import domain.entity.Order;
import domain.entity.OrderCode;
import domain.repository.ItemRepository;
import domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Checkout {

    private static final String SUCESS_MESSAGE = "Pedido realizado com sucesso";
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    
    public void execute(CheckoutInputDTO dto){
         var order = new Order(dto.cpf());
         var ordersCount = orderRepository.count();
         var orderCode = OrderCode.generate(dto.year(), ordersCount.toString());
         dto.itemInputs().forEach(i->{
             var item =itemRepository.getItem(i.itemId());
             order.addItem(item);
             order.setCode(orderCode);
         });
         orderRepository.save(order);
    }

}
