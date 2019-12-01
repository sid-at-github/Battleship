package games.battleship.model;

import games.battleship.constant.ShipOrientation;
import games.battleship.constant.ShipType;

/**
 * The class ship represents a ship of battleship game.
 */
public class Ship {

	private final ShipType type;
	private final ShipOrientation orientation;
	private final ShipPosition startPosition;
	private int health;

	/**
	 * Ship is instantiated by providing its type, orientation and position.
	 * 
	 * @param type          type of ship
	 * @param orientation   orientation of ship
	 * @param startPosition start position of ship
	 * @throws IllegalArgumentException if any of the parameters are null
	 */
	public Ship(final ShipType type, final ShipOrientation orientation, final ShipPosition startPosition) {
		if (type == null || orientation == null || startPosition == null)
			throw new IllegalArgumentException("unable to construct ship none of the parameters can be null");
		this.type = type;
		this.orientation = orientation;
		this.startPosition = startPosition;
		this.health = type.getSize();
	}

	/**
	 * Returns type of ship.
	 * 
	 * @return type of ship.
	 */
	public ShipType getType() {
		return type;
	}

	/**
	 * Returns orientation of ship.
	 * 
	 * @return orientation of ship.
	 */
	public ShipOrientation getOrientation() {
		return orientation;
	}

	/**
	 * Returns start position of ship on player's board.
	 * 
	 * @return start position of ship on player's board.
	 */
	public ShipPosition getStartPosition() {
		return startPosition;
	}

	/**
	 * Returns end position of ship on player's board.
	 * 
	 * @return end position of ship on player's board.
	 */
	public ShipPosition getEndPosition() {
		if (orientation.equals(ShipOrientation.HORIZONTAL)) {
			return new ShipPosition(getStartPosition().getX(), getStartPosition().getY() + type.getSize() - 1);
		} else {
			return new ShipPosition(getStartPosition().getX() + type.getSize() - 1, getStartPosition().getY());
		}
	}

	/**
	 * Returns true if ship is present on given coordinate false otherwise.
	 * 
	 * @param x x coordinate
	 * @param y y coordinate
	 * @return true if ship is present on given coordinate false otherwise.
	 */
	public boolean isPresentOnPosition(int x, int y) {
		if ((x >= startPosition.getX() && x <= getEndPosition().getX())
				&& (y >= startPosition.getY() && y <= getEndPosition().getY())) {
			return true;
		}
		return false;
	}

	/**
	 * Ship has attribute health. Health is initialized to length of ship (For e.g,
	 * DESTROYER is of length 3). This method decreases heath of ship.
	 * 
	 */
	public void hit() {
		if (health > 0)
			health--;
	}

	/**
	 * Ship has attribute health. Health is initialized to length of ship (For e.g,
	 * DESTROYER is of length 3). Ship is considered destroyed if its health is 0.
	 * 
	 */
	public boolean isDestroyed() {
		return health <= 0;
	}
}
