package kalah.repository;

import java.util.HashMap;

import kalah.model.GameBoard;

import org.springframework.stereotype.Repository;

@Repository
public class GameRepository {

	private static HashMap<String, GameBoard> gamesInMemory = new HashMap<String, GameBoard>();
	
	public GameBoard findGameBoard(String userId) {
		return gamesInMemory.get(userId);
	}
	
	public void insertGameBoard(String userId, GameBoard board) {
		if(userId != null && board!=null)
			gamesInMemory.put(userId, board);
	}
}
