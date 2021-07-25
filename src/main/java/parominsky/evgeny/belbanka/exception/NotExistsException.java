package parominsky.evgeny.belbanka.exception;

public class NotExistsException extends Exception {
    public NotExistsException() {
        super("not.found");
    }

    public NotExistsException(String msg) {
        super(msg);
    }
}
