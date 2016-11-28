package kalah.validator;

import kalah.model.GameBoard;
import kalah.model.Move;

public class MoveValidator extends GameplayValidator{

	public MoveValidator(GameplayValidator nextValidator, String validInputMsg,
			String invalidInputMsg) {
		super(nextValidator, validInputMsg, invalidInputMsg);
	}

	@Override
	String validate(GameBoard gameBoard, Move move) {
		return (gameBoard.getPitCount(move.getPlayerId(), move.getPitId())>0) ? 
				(this.nextValidator != null) ? this.nextValidator.validate(gameBoard, move) : this.validInputMsg 
				: this.invalidInputMsg;

	}

}
