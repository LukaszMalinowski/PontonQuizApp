package pl.org.ponton.pontonquizapp.exceptions;

public class NoMoreQuestionsException extends Exception {

    public NoMoreQuestionsException() {
    }

    public NoMoreQuestionsException(String message) {
        super(message);
    }

    public NoMoreQuestionsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoMoreQuestionsException(Throwable cause) {
        super(cause);
    }
}
