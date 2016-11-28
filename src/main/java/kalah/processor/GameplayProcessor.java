package kalah.processor;

import java.util.Iterator;

import kalah.model.GameBoard;
import kalah.model.Section;

import org.springframework.stereotype.Component;

@Component
public class GameplayProcessor {

	public void process(GameBoard gameBoard, int playerId, int pitId) {
		int currentPlayerId = playerId;
		//int currentPitId = pitId+1;

		Section[] pitArr = gameBoard.getPitMap().get(currentPlayerId);
		int numOfStones = pitArr[pitId].getNumOfStones();
		pitArr[pitId].setCountToZero();
		
		/*while(numOfStones>0) {
			for(int i = currentPitId; i<pitArr.length && numOfStones>0;i++) {
				pitArr[i].increaseCount();
				lastProcessedElm=pitArr[i];
				numOfStones--;
			}
			
			if(numOfStones>0) {
				Section kalah = gameBoard.getKalahMap().get(currentPlayerId);
				kalah.increaseCount();
				gameBoard.getKalahMap().put(currentPlayerId, kalah);
				lastProcessedElm=kalah;
				numOfStones--;
				currentPitId=0;
				currentPlayerId=(currentPlayerId==Player._1.getId()) ? Player._2.getId() : Player._1.getId();
				pitArr = gameBoard.getPitMap().get(currentPlayerId);
			}		
		}*/
		
		boolean startProcess = false;
		Section lastSection = null;
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
	}
	
	private void processAfterMove(GameBoard gameBoard) {
		
	}

}
