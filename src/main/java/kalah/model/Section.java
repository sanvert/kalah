package kalah.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Section {

	public enum SectionType {
		KALAH, PIT;
	}
	
	private int id;
	private AtomicInteger numOfStones;
	private SectionType type;
	public Player player;
	
	public Section(int id, int numOfStones, SectionType type, Player player) {
		this.id = id;
		this.numOfStones = new AtomicInteger(numOfStones);
		this.type = type;
		this.player = player;
	}

	public int getId() {
		return id;
	}

	public int getNumOfStones() {
		return numOfStones.get();
	}

	public void setNumOfStones(int numOfStones) {
		this.numOfStones = new AtomicInteger(numOfStones);
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public void setCountToZero() {
		this.numOfStones.set(0);
	}
	
	public void addStones(int delta) {
		this.numOfStones.addAndGet(delta);
	}
	
	public void increaseCount() {
		this.numOfStones.incrementAndGet();
	}
	
	public void decreaseCount() {
		this.numOfStones.decrementAndGet();
	}

	public boolean isKalah() {
		return this.type == SectionType.KALAH;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((player == null) ? 0 : player.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Section other = (Section) obj;
		if (id != other.id)
			return false;
		if (player != other.player)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	
	
}
