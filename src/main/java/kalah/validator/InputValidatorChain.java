package kalah.validator;

import kalah.model.GameBoard;
import kalah.model.Move;

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
	
	public String validateInput(GameBoard gameBoard, Move move) {
		return validator.validate(gameBoard, move);
	}
	
}