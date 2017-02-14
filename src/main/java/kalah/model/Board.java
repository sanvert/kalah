package kalah.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashSet;

import kalah.model.Section.SectionType;

public class Board implements Serializable {

	private final String boardId;
	private boolean gameEnded;
	private int currentPlayerId;
	private HashMap<Integer, Section> kalahMap;
	private HashMap<Integer, Section[]> pitMap;
	private LinkedHashSet<Section> boardSections;	

	public Board(String boardId) {
		this.boardId = boardId;
	}

	public Board(String boardId, int initialStoneCount) {
		this.boardId = boardId;
		currentPlayerId=Player._1.getId();
		
		kalahMap = new HashMap<Integer, Section>();
		pitMap = new HashMap<Integer, Section[]>();
		boardSections = new LinkedHashSet<Section>();

		int initialKalahStoneCount = 0;
		//Player 1 side of the board.
		Section kalah1 = new Section(Player._1.getId(), initialKalahStoneCount,  SectionType.KALAH, Player._1);	
		kalahMap.put(Player._1.getId(), kalah1);		
		Section[] arr1 = new Section[initialStoneCount];
		for(int i = 0; i<initialStoneCount; i++) {
			Section pit = new Section(i, initialStoneCount, SectionType.PIT, Player._1);
			arr1[i]=pit;
			boardSections.add(pit);
		}
		pitMap.put(Player._1.getId(), arr1);		
		boardSections.add(kalah1);

		//Player 2 side of the board.
		Section kalah2 = new Section(Player._2.getId(), initialKalahStoneCount,  SectionType.KALAH, Player._2);
		kalahMap.put(Player._2.getId(), kalah2);
		Section[] arr2 = new Section[initialStoneCount];
		for(int i = 0; i<initialStoneCount; i++) {
			Section pit = new Section(i, initialStoneCount, SectionType.PIT, Player._2);
			arr2[i]=pit;
			boardSections.add(pit);
		}
		pitMap.put(Player._2.getId(), arr2);	
		boardSections.add(kalah2);

	}
	
	public Section getOppositeSection(Section current) {
		Section opposite = null;
		if(!current.isKalah()) {
			int oppositePlayerId = (current.getPlayer().getId()==Player._1.getId()) ? Player._2.getId(): Player._1.getId();
			Section[] oppositeArr = pitMap.get(oppositePlayerId);
			opposite = oppositeArr[oppositeArr.length-current.getId()-1];
		}
		return opposite;
	}
	
	public int getPlayerStoneCountInPits(int playerId) {
		int count = 0;
		for (Section s: pitMap.get(playerId)) {
			count+=s.getNumOfStones();
		}
		return count;
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

	public int getCurrentPlayerId() {
		return currentPlayerId;
	}
	
	public HashMap<Integer, Section> getKalahMap() { return kalahMap; }

	public HashMap<Integer, Section[]> getPitMap() {
		return pitMap;
	}

	public LinkedHashSet<Section> getBoardSections() {
		return boardSections;
	}

	public String getBoardId() { return boardId; }

	public boolean isGameEnded() {
		return gameEnded;
	}
	
	public void setGameEnded(boolean gameEnded) {
		this.gameEnded=gameEnded;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Board board = (Board) o;

		return gameEnded == board.gameEnded
				&& boardId.equals(board.boardId);
	}

	@Override
	public int hashCode() {
		return boardId.hashCode();
	}
}
