package kalah.validator;

import kalah.model.Board;
import kalah.model.Move;
import kalah.model.ValidationResult;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class InputValidatorChain implements InitializingBean {

	private static final String INVALID_PLAYER_ORDER_MSG="INVALID PLAYER";
	private static final String INVALID_MOVE_MSG="INVALID MOVE";

	public static final String VALID_MSG="OK";

	private GameplayValidator validator;
	
	@Override
	public void afterPropertiesSet() {
		//Prepare Input Validation Chain
		MoveValidator mv = new MoveValidator(null, VALID_MSG, INVALID_MOVE_MSG);
		PlayerOrderValidator pov = new PlayerOrderValidator(mv, VALID_MSG, INVALID_PLAYER_ORDER_MSG);
		this.validator = pov;
	}
	
	public ValidationResult validateInput(Board board, Move move) {
		return new ValidationResult(validator.validate(board, move));
	}
	
}
