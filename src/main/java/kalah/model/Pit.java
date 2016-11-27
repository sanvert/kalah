package kalah.model;

public class Pit {

	private int numOfStones;

	public Pit(int numOfStones) {
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
	
	public void setCountToZero() {
		this.numOfStones=0;
	}
	
}
