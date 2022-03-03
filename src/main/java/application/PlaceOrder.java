package application;

import java.time.LocalDateTime;
import java.util.UUID;

public class PlaceOrder {

    public String generatePlaceOrderCode() {
        var year = String.valueOf(LocalDateTime.now().getYear());
        var code = UUID.randomUUID().toString().substring(0, 8);
        return year.concat(code);
    }
}
