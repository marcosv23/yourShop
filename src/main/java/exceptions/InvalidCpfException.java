package exceptions;

public class InvalidCpfException extends RuntimeException {
    private static final String MESSAGE = "There is not permitted to make an order with invalid CPF";

    public InvalidCpfException() {
        super(MESSAGE);
    }

}
