package gravytrain.exceptions;

public class ActiveUserException extends Exception {

	private static final long serialVersionUID = 1L;

	public ActiveUserException() {
		super();
	}

	public ActiveUserException(String message) {
		super(message);
	}
}
