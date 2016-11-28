package kalah.validator;

import kalah.model.GameBoard;
import kalah.model.Move;

public abstract class GameplayValidator {
	protected GameplayValidator nextValidator;
	protected String validInputMsg;
	protected String invalidInputMsg;
	
	protected GameplayValidator(GameplayValidator nextValidator, String validInputMsg, String invalidInputMsg) {
		this.nextValidator=nextValidator;
		this.validInputMsg=validInputMsg;
		this.invalidInputMsg=invalidInputMsg;
	}
	
	abstract String validate(GameBoard gameBoard, Move move);
}
