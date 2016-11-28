package kalah.service;

import java.util.UUID;

import kalah.factory.GameFactory;
import kalah.model.GameBoard;
import kalah.model.Move;
import kalah.processor.GameplayProcessor;
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
	
	@Autowired
	private GameplayProcessor gameplayProcessor;
	
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
		GameBoard currentBoard = getUserBoard(move.getUser());

		if(currentBoard != null) {
			gameplayProcessor.process(currentBoard, move.getPlayerId(), move.getPitId());
			//gameRepository.insertGameBoard(gameId, currentBoard);
		}
		
		return currentBoard;
	}
	
	public GameBoard getUserBoard(String user) {
		GameBoard board = null;
		String gameId = userGameRepository.findGameBoard(user);
		if(gameId!=null) {
			board = gameRepository.findGameBoard(gameId);
		}
		return board;
	}
	
}
