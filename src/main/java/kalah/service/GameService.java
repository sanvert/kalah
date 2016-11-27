package kalah.service;

import java.util.UUID;

import kalah.factory.GameFactory;
import kalah.model.GameBoard;
import kalah.model.Move;
import kalah.repository.GameRepository;
import kalah.repository.UserGameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private UserGameRepository userGameRepository;
	
	@Autowired
	private GameFactory gameFactory;
	
	public GameBoard getGame(String user) {
		//TO-DO:Put input based lock
		GameBoard gameBoard = null;
		if(user != null) {
			String gameId = userGameRepository.findGameBoard(user);
			if(gameId != null) {
				gameBoard = gameRepository.findGameBoard(gameId);
			} else {
				gameId = UUID.randomUUID().toString();
				gameBoard = gameFactory.generateNewGame();
				userGameRepository.insertGameBoard(user, gameId);
				gameRepository.insertGameBoard(gameId, gameBoard);
			}
		}	
		return gameBoard;
	}
	
	public GameBoard play(Move move) {
		//TO-DO:Put input based lock
		GameBoard currentBoard = null;
		String gameId = userGameRepository.findGameBoard(move.getUser());
		if(gameId != null) {
			currentBoard = gameRepository.findGameBoard(gameId);
			if(currentBoard != null) {
				currentBoard.playOnPit(move.getPlayerId(), move.getPitId());
				gameRepository.insertGameBoard(gameId, currentBoard);
			}
		}
		return currentBoard;
	}
}
