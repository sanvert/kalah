package kalah.validator;

import kalah.model.Board;
import kalah.model.Move;

/**
 * Abstract validator for input validation chain implementations.
 */
public abstract class GameplayValidator {
	protected GameplayValidator nextValidator;
	protected String validInputMsg;
	protected String invalidInputMsg;
	
	protected GameplayValidator(GameplayValidator nextValidator, String validInputMsg, String invalidInputMsg) {
		this.nextValidator=nextValidator;
		this.validInputMsg=validInputMsg;
		this.invalidInputMsg=invalidInputMsg;
	}
	
	protected abstract String validate(Board board, Move move);
}
