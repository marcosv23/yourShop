package application.input.checkout;

import java.util.List;

public record CheckoutInputDTO(List<ItemInputDTO> itemInputs, String cpf, int year) {
}

