package Prog4.Station.exception;

public class BadValueException extends RuntimeException {
    public BadValueException(String message) {
        super((message));
    }
}
