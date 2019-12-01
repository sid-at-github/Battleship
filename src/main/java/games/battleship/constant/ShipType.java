package games.battleship.constant;

/**
 * 
 * The enum ShipType contains the permitted ship types.
 *
 */
public enum ShipType {

	CARRIER(5), BATTLESHIP(4), DESTROYER(3), SUBMARINE(3), PATROL_BOAT(2);

	private int size;

	ShipType(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
