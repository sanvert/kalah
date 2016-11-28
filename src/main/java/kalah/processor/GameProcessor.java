package kalah.processor;

import java.util.Iterator;

import kalah.model.GameBoard;
import kalah.model.Player;
import kalah.model.Section;

import org.springframework.stereotype.Component;

@Component
public class GameProcessor {

	public void process(GameBoard gameBoard, int playerId, int pitId) {
		Section[] pitArr = gameBoard.getPitMap().get(playerId);
		int numOfStones = pitArr[pitId].getNumOfStones();
		pitArr[pitId].setCountToZero();
		
		boolean startProcess = false;
		Section lastSection = null;
		//Iterate over gameboard with guava cyclic iterator.
		Iterator<Section> cyclicIterator = jersey.repackaged.com.google.common.collect.Iterators.cycle(gameBoard.getBoardSections());
		while(numOfStones>0) {
			lastSection = cyclicIterator.next();
			if(startProcess) {
				lastSection.increaseCount();
				numOfStones--;
			} else if(lastSection.equals(pitArr[pitId])) {
				startProcess=true;
			}
		}
		
		if(lastSection != null)
			checkLastProcessedSectionAndUpdateForNewTurn(gameBoard, lastSection);
	}
	
	private void checkLastProcessedSectionAndUpdateForNewTurn(GameBoard gameBoard, Section lastSection) {
		if(lastSection.isKalah()) {
			// Check the last added seed is added into kalah and if so give another turn for current user.
			if(lastSection.getPlayer().getId()!=gameBoard.getCurrentPlayerId())
				gameBoard.changeCurrentPlayer();
		} else {
			// Check the last added seed is added into an empty house and opposite house is filled with seed or seeds.
			Section oppositeSection = gameBoard.getOppositeSection(lastSection);
			if(lastSection.getPlayer().getId() == gameBoard.getCurrentPlayerId()
					&& lastSection.getNumOfStones()==1 
					&& oppositeSection.getNumOfStones()>0) {
				gameBoard.getKalahMap().get(gameBoard.getCurrentPlayerId()).addStones(lastSection.getNumOfStones() + oppositeSection.getNumOfStones());
				lastSection.setCountToZero();
				oppositeSection.setCountToZero();
			} else {
				gameBoard.changeCurrentPlayer();
			}
		}
		
		// Check if current user can play turn.
		if(gameBoard.getPlayerStoneCountInPits(gameBoard.getCurrentPlayerId())==0)
			gameBoard.changeCurrentPlayer();
		
		checkIfGameEnded(gameBoard);
	}
	
	private void checkIfGameEnded(GameBoard gameBoard) {
		if(gameBoard.getPlayerStoneCountInPits(Player._1.getId())==0 
				&& gameBoard.getPlayerStoneCountInPits(Player._2.getId())==0)
			gameBoard.setGameEnded(true);
	}

}
