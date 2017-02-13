package kalah.repository;

import kalah.model.Board;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class BoardRepository {

	private static HashMap<String, Board> gamesInMemory = new HashMap<String, Board>();
	
	public Board findBoard(String boardIdForCurrentGame) {
		return gamesInMemory.get(boardIdForCurrentGame);
	}
	
	public void insertBoard(String boardIdForCurrentGame, Board board) {
		if(boardIdForCurrentGame != null && board!=null)
			gamesInMemory.put(boardIdForCurrentGame, board);
	}
}
