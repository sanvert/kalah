package kalah.filters;

import kalah.model.GameBoard;
import kalah.model.Move;

public abstract class GameplayPreProcessHandler {
	private GameplayPreProcessHandler nextHandler;
	
	abstract boolean processor(GameBoard gameBoard, Move move);
}
