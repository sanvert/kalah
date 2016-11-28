package kalah.validator;

import kalah.model.GameBoard;
import kalah.model.Move;

public class PlayerOrderValidator extends GameplayValidator{
	
	public PlayerOrderValidator(GameplayValidator nextValidator,
			String validInputMsg, String invalidInputMsg) {
		super(nextValidator, validInputMsg, invalidInputMsg);
	}

	@Override
	String validate(GameBoard gameBoard, Move move) {
		return (gameBoard.getCurrentPlayerId() == move.getPlayerId()) ? 
				(this.nextValidator != null) ? this.nextValidator.validate(gameBoard, move) : this.validInputMsg 
				: this.invalidInputMsg;
	}
	
}
