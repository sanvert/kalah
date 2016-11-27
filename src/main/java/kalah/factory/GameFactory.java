package kalah.factory;

import kalah.model.GameBoard;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GameFactory {

	@Value("${initialStoneCount}")
	private int initialStoneCount;
	
	public GameBoard generateNewGame() {
		return new GameBoard(initialStoneCount);
	}
	
}
