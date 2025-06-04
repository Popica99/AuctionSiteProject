package sdacademy.auctionsiteproject.exceptions;

public class PriceTooLowException extends RuntimeException {
    public PriceTooLowException(String message) {
        super(message);
    }
}
