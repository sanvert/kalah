package kalah.model;

import java.util.ArrayList;
import java.util.HashMap;



public class GameBoard {
	
	private enum Id{
		_1(1), _2(2);
		
		private int val;
		private Id(int val) {
			this.val=val;
		}
		public int getVal() {
			return val;
		}
	}
	
	private int currentPlayerId;
	private ArrayList<Player> playerList;
	private HashMap<Integer, Kalah> kalahMap;
	private HashMap<Integer, Pit[]> pitMap;
	
	public GameBoard(int initialStoneCount) {
		currentPlayerId=Id._1.getVal();
		Player p1 = new Player(Id._1.getVal());
		Player p2 = new Player(Id._2.getVal());
		playerList = new ArrayList<Player>();
		playerList.add(p1);
		playerList.add(p2);
		
		Kalah k1 = new Kalah(0);
		Kalah k2 = new Kalah(0);
		kalahMap = new HashMap<Integer, Kalah>();
		kalahMap.put(p1.getId(), k1);
		kalahMap.put(p2.getId(), k2);
		
		//create pits for players 
		Pit[] arr1 = new Pit[initialStoneCount];
		Pit[] arr2 = new Pit[initialStoneCount];
		for(int i = 0; i<initialStoneCount; i++) {
			arr1[i] = new Pit(initialStoneCount);
			arr2[i] = new Pit(initialStoneCount);
		}
		pitMap = new HashMap<Integer, Pit[]>();
		pitMap.put(p1.getId(), arr1);
		pitMap.put(p2.getId(), arr2);
		
	}
	
	public void changeCurrentPlayer() {
		this.currentPlayerId=(this.currentPlayerId==Id._1.getVal()) ? Id._2.getVal() : Id._1.getVal();
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
				currentPlayerId=(currentPlayerId==Id._1.getVal()) ? Id._2.getVal() : Id._1.getVal();
				pitArr = pitMap.get(currentPlayerId);
			}		
		}
	}

	public int getCurrentPlayerId() {
		return currentPlayerId;
	}

	public ArrayList<Player> getPlayerList() {
		return playerList;
	}

	public HashMap<Integer, Kalah> getKalahMap() {
		return kalahMap;
	}

	public HashMap<Integer, Pit[]> getPitMap() {
		return pitMap;
	}	
	
	
}
