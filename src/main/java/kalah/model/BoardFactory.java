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
		return new Board(UUID.randomUUID().toString(), initialStoneCount);
	}

	/**
	 * Creates a new board object.
	 * @param boardId given
	 * @return {@link Board}
	 */
	public Board generate(String boardId) {
		return new Board(boardId, initialStoneCount);
	}
	
}
