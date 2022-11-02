package application.output;

import java.math.BigDecimal;

public record OrdersOutput(String cpf,
                           String code,
                           String description,
                           BigDecimal totalPrice){}
