package parominsky.evgeny.belbanka.exception;

public class UserNotFoundException extends Exception{
    public UserNotFoundException() {
        super("notFound.message");
    }
}
