

public class WrongPeselException extends Exception { 
    public WrongPeselException(String errorMessage) {
        super("Its wrong pesel!");
    }
}