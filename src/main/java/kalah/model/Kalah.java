package kalah.model;

public class Kalah {

	private int numOfStones;

	public Kalah(int numOfStones) {
		super();
		this.numOfStones = numOfStones;
	}

	public int getNumOfStones() {
		return numOfStones;
	}

	public void increaseCount() {
		this.numOfStones++;
	}
	
	public void decreaseCount() {
		this.numOfStones--;
	}
}
