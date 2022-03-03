package application.input;

import domain.entity.OrderItem;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Builder
public class InputPlaceOrder {
    private String cpf;
    private List<OrderItem> items;


}
