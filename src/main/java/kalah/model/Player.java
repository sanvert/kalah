package kalah.model;

/**
 * Player enumerator representing game players for 2-player "Kalah" game.
 */
public enum Player {
	_1(1), _2(2);
	
	private int id;
	private Player(int id) {
		this.id=id;
	}
	public int getId() {
		return id;
	}
}
