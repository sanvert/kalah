package kalah.model;

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
