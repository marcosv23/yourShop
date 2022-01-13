package exceptions;

public class InvalidCouponException extends RuntimeException {
    private static final String MESSAGE = "There is not permitted to make an order with invalid CPF";

    public InvalidCouponException() {
        super(MESSAGE);
    }

}
