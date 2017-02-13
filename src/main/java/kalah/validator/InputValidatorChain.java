package kalah.validator;

import kalah.model.Board;
import kalah.model.Move;
import kalah.model.ValidationResult;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class InputValidatorChain implements InitializingBean {

	public static final String VALID_MSG="OK";
	
	private static final String INVALID_PLAYER_ORDER_MSG="INVALID PLAYER";
	private static final String INVALID_MOVE_MSG="INVALID MOVE";

	private GameplayValidator validator;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		//Prepare Input Validation Chain
		PlayerOrderValidator pov = new PlayerOrderValidator(null, VALID_MSG, INVALID_PLAYER_ORDER_MSG);
		MoveValidator mv = new MoveValidator(pov, VALID_MSG, INVALID_MOVE_MSG);
		validator = mv;
	}
	
	public ValidationResult validateInput(Board board, Move move) {
		return new ValidationResult(validator.validate(board, move));
	}
	
}
