package application.input.checkout;

import java.util.List;

public record CheckoutInputWithCodeDTO(List<ItemInputDTO> itemInputs,
                                       String cpf,
                                       String orderCode) {
}

