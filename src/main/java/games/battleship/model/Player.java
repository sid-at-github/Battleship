package games.battleship.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import games.battleship.constant.PlayerId;
import games.battleship.constant.ShipOrientation;
import games.battleship.constant.ShipType;

/**
 * The class player represents a player of battleship game.
 */
public class Player {

	private final PlayerId id;
	private String name;
	private Board board;
	private List<Ship> ships;

	/**
	 * Player is instantiated by providing its id, name and board object.
	 * 
	 * @param id    player identifier
	 * @param name  player name
	 * @param board player board object
	 * @throws IllegalArgumentException if any of the parameters are null or empty
	 */
	public Player(final PlayerId id, String name, Board board) {
		super();
		if (id == null || name == null || name.isEmpty() || board == null)
			throw new IllegalArgumentException(
					"unable to construct player none of the parameters can be null or empty");
		this.id = id;
		this.name = name;
		this.board = board;
		ships = new ArrayList<>();
	}

	/**
	 * Returns player identifier.
	 * 
	 * @return player identifier.
	 */
	public PlayerId getId() {
		return id;
	}

	/**
	 * Returns player name.
	 * 
	 * @return player name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns player board object.
	 * 
	 * @return player board object.
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Sets player board object.
	 * 
	 * @param board board object to set
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

	/**
	 * Returns player ships.
	 * 
	 * @return player ships.
	 */
	public List<Ship> getShips() {
		return ships;
	}

	/**
	 * Sets a player ships.
	 * 
	 * @param ships ships
	 */
	public void setShips(List<Ship> ships) {
		this.ships = ships;
	}

	/**
	 * Returns true if given player is the dead false otherwise.
	 * 
	 * @return true if given player is the dead false otherwise.
	 */
	public boolean isDead() {
		for (Ship ship : ships) {
			if (!ship.isDestroyed())
				return false;
		}
		return true;
	}

	/**
	 * Returns ship on given coordinates.
	 * 
	 * @param x x coordinate
	 * @param y y coordinate
	 * @return ship on given coordinate.
	 */
	public Ship getShipOnBoardPosition(int x, int y) {
		Ship shipOnGivenPosition = null;
		for (Ship ship : ships) {
			if (ship.isPresentOnPosition(x, y)) {
				shipOnGivenPosition = ship;
				break;
			}
		}
		return shipOnGivenPosition;
	}

	/**
	 * This method places all the ships randomly on player's board.
	 * 
	 */
	public void placeShipsRandomly() {
		Random random = new Random();
		for (ShipType shipType : ShipType.values()) {
			while (true) {
				ShipOrientation orientation = random.nextBoolean() ? ShipOrientation.HORIZONTAL
						: ShipOrientation.VERTICAL;
				int x = orientation.equals(ShipOrientation.HORIZONTAL)
						? random.nextInt(board.getSize().getValue() - shipType.getSize())
						: random.nextInt(shipType.getSize());
				int y = orientation.equals(ShipOrientation.VERTICAL)
						? random.nextInt(board.getSize().getValue() - shipType.getSize())
						: random.nextInt(shipType.getSize());
				Ship ship = new Ship(shipType, orientation, new ShipPosition(x, y));
				if (!board.canPlaceShip(ship)) {
					continue;
				} else {
					ships.add(ship);
					board.placeShip(ship);
					break;
				}
			}
		}
	}

}
