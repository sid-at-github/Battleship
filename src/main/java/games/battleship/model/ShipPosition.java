package games.battleship.model;

/**
 * 
 * The class ShipPosition stores the coordinates of the ship position. It can be
 * used to store start coordinates and end coordinates of the ship position.
 *
 */
public class ShipPosition {

	private final int x;
	private final int y;

	/**
	 * ShipPosition is instantiated by providing the starting coordinates of ship
	 * position.
	 * 
	 * @param x coordinate x
	 * @param y coordinate y
	 * @throws IllegalArgumentException if either of the coordinate position is
	 *                                  negative
	 */
	public ShipPosition(final int x, final int y) {
		if (x < 0 || y < 0)
			throw new IllegalArgumentException("neither of the coordinated can be -ve");
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns x coordinate of ship position.
	 * 
	 * @return x coordinate of ship position.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns y coordinate of ship position.
	 * 
	 * @return y coordinate of ship position.
	 */
	public int getY() {
		return y;
	}

}
