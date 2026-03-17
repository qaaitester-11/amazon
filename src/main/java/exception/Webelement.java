package exception;

public class Webelement extends RuntimeException {

	public Webelement(String message) {
		super(message);
	}
	
	
	public Webelement(String message, Throwable thors) {
		
		super(message, thors);
		
	}

}
