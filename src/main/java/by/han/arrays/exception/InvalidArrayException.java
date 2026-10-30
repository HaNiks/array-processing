package by.han.arrays.exception;

public class InvalidArrayException extends Exception {
    public InvalidArrayException(String message) {
        super(message);
    }

    public InvalidArrayException(String message, Throwable cause) {
        super(message, cause);
    }
}