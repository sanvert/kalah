package kalah.repository;

import java.util.HashMap;

import kalah.model.GameBoard;

import org.springframework.stereotype.Repository;

@Repository
public class GameRepository {

	private static HashMap<String, GameBoard> gamesInMemory = new HashMap<String, GameBoard>();
	
	public GameBoard findGameBoard(String boardId) {
		return gamesInMemory.get(boardId);
	}
	
	public void insertGameBoard(String boardId, GameBoard board) {
		if(boardId != null && board!=null)
			gamesInMemory.put(boardId, board);
	}
}
