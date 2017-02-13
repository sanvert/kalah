package kalah.validator;

import kalah.model.Board;
import kalah.model.Move;

public class PlayerOrderValidator extends GameplayValidator{
	
	public PlayerOrderValidator(GameplayValidator nextValidator,
			String validInputMsg, String invalidInputMsg) {
		super(nextValidator, validInputMsg, invalidInputMsg);
	}

	@Override
	protected String validate(Board board, Move move) {
		return (board.getCurrentPlayerId() == move.getPlayerId()) ?
				(this.nextValidator != null) ? this.nextValidator.validate(board, move) : this.validInputMsg
				: this.invalidInputMsg;
	}
	
}
