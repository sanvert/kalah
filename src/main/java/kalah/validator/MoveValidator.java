package kalah.validator;

import kalah.model.Board;
import kalah.model.Move;

public class MoveValidator extends GameplayValidator{

	public MoveValidator(GameplayValidator nextValidator, String validInputMsg,
			String invalidInputMsg) {
		super(nextValidator, validInputMsg, invalidInputMsg);
	}

	@Override
	protected String validate(Board board, Move move) {
		return (board.getPitCount(move.getPlayerId(), move.getPitId())>0) ?
				(this.nextValidator != null) ? this.nextValidator.validate(board, move) : this.validInputMsg
				: this.invalidInputMsg;

	}

}
