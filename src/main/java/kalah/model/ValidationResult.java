package kalah.model;

/**
 * Validation object contains message for the result of move command.
 */
public class ValidationResult {

	public String message;
	
	public ValidationResult(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
