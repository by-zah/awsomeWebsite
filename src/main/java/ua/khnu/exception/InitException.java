package ua.khnu.exception;

public class InitException extends RuntimeException {
    public InitException(String message) {
        super(message);
    }
    public InitException(String message,Exception e){
        super(message,e);
    }
}
