package parominsky.evgeny.belbanka.exception;

public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException() {
        super("uniqueName.message");
    }
}
