package kalah.processor;

import java.util.Iterator;

import kalah.model.Board;
import kalah.model.Player;
import kalah.model.Section;

import org.springframework.stereotype.Component;

@Component
public class BoardProcessor {

	public void process(Board board, int playerId, int pitId) {
		Section[] pitArr = board.getPitMap().get(playerId);
		int numOfStones = pitArr[pitId].getNumOfStones();
		pitArr[pitId].setCountToZero();
		
		boolean startProcess = false;
		Section lastSection = null;
		//Iterate over board with guava cyclic iterator to spread stones over pits.
		Iterator<Section> cyclicIterator = jersey.repackaged.com.google.common.collect.Iterators.cycle(board.getBoardSections());
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
			checkLastProcessedSectionAndUpdateForNewTurn(board, lastSection);
		
		checkIfGameEnded(board);
	}
	
	private void checkLastProcessedSectionAndUpdateForNewTurn(Board board, Section lastSection) {
		if(lastSection.isKalah()) {
			// Check the last added seed is added into kalah and if so give another turn for current user.
			if(lastSection.getPlayer().getId()!=board.getCurrentPlayerId())
				board.changeCurrentPlayer();
		} else {
			// Check the last added seed is added into an empty house and opposite house is filled with seed or seeds.
			Section oppositeSection = board.getOppositeSection(lastSection);
			if(lastSection.getPlayer().getId() == board.getCurrentPlayerId()
					&& lastSection.getNumOfStones()==1 
					&& oppositeSection.getNumOfStones()>0) {
				board.getKalahMap().get(board.getCurrentPlayerId()).addStones(lastSection.getNumOfStones() + oppositeSection.getNumOfStones());
				lastSection.setCountToZero();
				oppositeSection.setCountToZero();
			} else {
				board.changeCurrentPlayer();
			}
		}
		
		// Check if current user can play turn.
		if(board.getPlayerStoneCountInPits(board.getCurrentPlayerId())==0)
			board.changeCurrentPlayer();
		
	}
	
	private void checkIfGameEnded(Board board) {
		if(board.getPlayerStoneCountInPits(Player._1.getId())==0
				&& board.getPlayerStoneCountInPits(Player._2.getId())==0)
			board.setGameEnded(true);
	}

}
