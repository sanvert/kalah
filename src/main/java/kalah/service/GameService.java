package kalah.service;

import java.util.concurrent.locks.Lock;

import kalah.factory.GameFactory;
import kalah.model.GameBoard;
import kalah.model.Move;
import kalah.processor.GameProcessor;
import kalah.repository.GameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.util.concurrent.Striped;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private GameFactory gameFactory;

	@Autowired
	private GameProcessor gameProcessor;

	private final Striped<Lock> STRIPED_LOCK = Striped.lock(2);

	public GameBoard newGame(String user) {
		// Input based locking mechanism added
		GameBoard gameBoard = null;
		try {
			STRIPED_LOCK.get(user).lock();
			gameBoard = gameFactory.generateNewGame();
			gameRepository.insertGameBoard(user, gameBoard);
		} finally {
			STRIPED_LOCK.get(user).unlock();
		}
		return gameBoard;
	}

	public GameBoard getGame(String user) {
		// Input based locking mechanism added
		GameBoard gameBoard = null;
		try {
			STRIPED_LOCK.get(user).lock();
			gameBoard = gameRepository.findGameBoard(user);
			if (gameBoard == null) {
				gameBoard = gameFactory.generateNewGame();
				gameRepository.insertGameBoard(user, gameBoard);
			}
		} finally {
			STRIPED_LOCK.get(user).unlock();
		}

		return gameBoard;
	}

	public GameBoard play(Move move) {
		// Input based lock
		GameBoard currentBoard = null;
		try {
			STRIPED_LOCK.get(move.getUser()).lock();
			currentBoard = gameRepository.findGameBoard(move.getUser());

			if (currentBoard != null) {
				gameProcessor.process(currentBoard, move.getPlayerId(),
						move.getPitId());
				// Insert updated board
				gameRepository.insertGameBoard(move.getUser(), currentBoard);
			}
		} finally {
			STRIPED_LOCK.get(move.getUser()).unlock();
		}

		return currentBoard;
	}

}
