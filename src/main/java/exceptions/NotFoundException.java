package exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String MESSAGE){
        super(MESSAGE);
    };

}
