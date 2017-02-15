package kalah.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Board factory service to provide new game boards.
 */
@Service
public class BoardFactory {

	@Value("${initialStoneCount:6}")
	private int initialStoneCount;

	/**
	 * Creates a new board object.
	 * @return {@link Board}
	 */
	public Board generate() {
		String boardId = UUID.randomUUID().toString();
		return new Board(boardId, initialStoneCount);
	}
	
}
