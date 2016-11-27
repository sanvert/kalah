package kalah.model;

public class Move {
	private String user;
	private int playerId;
	private int pitId;
	
	public Move() {
		
	}
	
	public Move(String user, int playerId, int pitId) {
		super();
		this.user = user;
		this.playerId = playerId;
		this.pitId = pitId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
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
