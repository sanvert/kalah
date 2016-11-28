package kalah.model;

import java.util.HashMap;

public class GameBoard {

	private int currentPlayerId;
	private HashMap<Integer, Kalah> kalahMap;
	private HashMap<Integer, Pit[]> pitMap;
	
	public GameBoard(int initialStoneCount) {
		currentPlayerId=Player._1.getId();
		
		Kalah k1 = new Kalah(0);
		Kalah k2 = new Kalah(0);
		kalahMap = new HashMap<Integer, Kalah>();
		kalahMap.put(Player._1.getId(), k1);
		kalahMap.put(Player._2.getId(), k2);
		
		//create pits for players 
		Pit[] arr1 = new Pit[initialStoneCount];
		Pit[] arr2 = new Pit[initialStoneCount];
		for(int i = 0; i<initialStoneCount; i++) {
			arr1[i] = new Pit(initialStoneCount);
			arr2[i] = new Pit(initialStoneCount);
		}
		pitMap = new HashMap<Integer, Pit[]>();
		pitMap.put(Player._1.getId(), arr1);
		pitMap.put(Player._2.getId(), arr2);
		
	}
	
	public void changeCurrentPlayer() {
		this.currentPlayerId=(this.currentPlayerId==Player._1.getId()) ? Player._2.getId() : Player._1.getId();
	}
	
	public int getKalahCount(int playerId) {
		return kalahMap.get(playerId).getNumOfStones();
	}
	
	public int getPitCount(int playerId, int pitId) {
		return pitMap.get(playerId)[pitId].getNumOfStones();
	}
	
	public void playOnPit(int playerId, int pitId) {
		int currentPlayerId = playerId;
		Pit[] pitArr = pitMap.get(currentPlayerId);
		int numOfStones = pitArr[pitId].getNumOfStones();
		pitArr[pitId].setCountToZero();
		
		int currentPitId = pitId+1;
		while(numOfStones>0) {
			for(int i = currentPitId; i<pitArr.length && numOfStones>0;i++) {
				pitArr[i].increaseCount();
				numOfStones--;
			}
			
			if(numOfStones>0) {
				Kalah kalah = kalahMap.get(currentPlayerId);
				kalah.increaseCount();
				kalahMap.put(currentPlayerId, kalah);
				numOfStones--;
				currentPitId=0;
				currentPlayerId=(currentPlayerId==Player._1.getId()) ? Player._2.getId() : Player._1.getId();
				pitArr = pitMap.get(currentPlayerId);
			}		
		}
	}

	public int getCurrentPlayerId() {
		return currentPlayerId;
	}
	
	public HashMap<Integer, Kalah> getKalahMap() {
		return kalahMap;
	}

	public HashMap<Integer, Pit[]> getPitMap() {
		return pitMap;
	}	
	
	
}
