package kalah.service;

import com.google.common.util.concurrent.Striped;
import kalah.model.Board;
import kalah.model.Move;
import kalah.processor.BoardProcessor;
import kalah.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.locks.Lock;

/**
 * Service instance providing operations to game controller.
 */
@Service
public class GameService {

	private final BoardRepository boardRepository;
	private final BoardProcessor boardProcessor;

	@Autowired
	public GameService(BoardRepository boardRepository, BoardProcessor boardProcessor) {
		this.boardRepository = boardRepository;
		this.boardProcessor = boardProcessor;
	}

	private final Striped<Lock> STRIPED_LOCK = Striped.lock(2);

	public Board createNewBoard() {
		return boardRepository.findBoard(UUID.randomUUID().toString());
	}

	public Board getBoard(String boardId) {
		return boardRepository.findBoard(boardId);
	}

	public Board play(Move move) {
		// Input based lock
		Board currentBoard = null;
		try {
			STRIPED_LOCK.get(move.getBoardId()).lock();
			currentBoard = boardRepository.findBoard(move.getBoardId());

			if (currentBoard != null) {
				boardProcessor.process(currentBoard, move.getPlayerId(), move.getPitId());
				// Updated Board is cached
			}
		} finally {
			STRIPED_LOCK.get(move.getBoardId()).unlock();
		}

		return currentBoard;
	}
}
