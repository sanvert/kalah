package kalah.model;

/**
 * Player move is represented by this object.
 */
public class Move {
	private String boardId;
	private int playerId;
	private int pitId;
	
	public Move() {
	}
	
	public Move(String boardId, int playerId, int pitId) {
		super();
		this.boardId = boardId;
		this.playerId = playerId;
		this.pitId = pitId;
	}

	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public int getPitId() {
		return pitId;
	}

	public void setPitId(int pitId) {
		this.pitId = pitId;
	}
	
}
