package exception;

public class NoSuchIdException extends Exception {
    public NoSuchIdException() {
        super("В коллекции нет элемента с таким id");
    }
}
