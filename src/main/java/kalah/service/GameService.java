package kalah.service;

import com.google.common.util.concurrent.Striped;
import kalah.factory.BoardFactory;
import kalah.model.Board;
import kalah.model.Move;
import kalah.processor.BoardProcessor;
import kalah.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;

@Service
public class GameService {

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private BoardFactory boardFactory;

	@Autowired
	private BoardProcessor boardProcessor;

	private final Striped<Lock> STRIPED_LOCK = Striped.lock(2);

	public Board newBoard(String generatedBoardId) {
		// Input based locking mechanism added
		Board board = null;
		try {
			STRIPED_LOCK.get(generatedBoardId).lock();
			board = boardFactory.generate();
			boardRepository.insertBoard(generatedBoardId, board);
		} finally {
			STRIPED_LOCK.get(generatedBoardId).unlock();
		}
		return board;
	}

	public Board getBoard(String generatedBoardId) {
		// Input based locking mechanism added
		Board board = null;
		try {
			STRIPED_LOCK.get(generatedBoardId).lock();
			board = boardRepository.findBoard(generatedBoardId);
			if (board == null) {
				board = boardFactory.generate();
				boardRepository.insertBoard(generatedBoardId, board);
			}
		} finally {
			STRIPED_LOCK.get(generatedBoardId).unlock();
		}

		return board;
	}

	public Board play(Move move) {
		// Input based lock
		Board currentBoard = null;
		try {
			STRIPED_LOCK.get(move.getUser()).lock();
			currentBoard = boardRepository.findBoard(move.getUser());

			if (currentBoard != null) {
				boardProcessor.process(currentBoard, move.getPlayerId(),
						move.getPitId());
				// Insert updated board
				boardRepository.insertBoard(move.getUser(), currentBoard);
			}
		} finally {
			STRIPED_LOCK.get(move.getUser()).unlock();
		}

		return currentBoard;
	}

}
