package exceptions;

public class InvalidCouponException extends RuntimeException {
    private static final String MESSAGE = "Coupon is invalid or expired";

    public InvalidCouponException() {
        super(MESSAGE);
    }

}
