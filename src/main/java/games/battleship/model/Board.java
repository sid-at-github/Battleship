package games.battleship.model;

import java.util.Arrays;

import games.battleship.constant.BoardSize;
import games.battleship.constant.TileType;

/**
 * The class board represents a player's board.
 */
public class Board {

	private TileType[][] board;

	private final BoardSize size;

	/**
	 * Board is instantiated by providing its size.
	 * 
	 * @param size board size
	 * @throws IllegalArgumentException if board size is null.
	 */
	public Board(final BoardSize size) {
		if (size == null)
			throw new IllegalArgumentException("size can not be null");
		this.size = size;
		board = new TileType[size.getValue()][size.getValue()];
		for (TileType[] row : board)
			Arrays.fill(row, TileType.WATER);
	}

	/**
	 * Returns size of board.
	 * 
	 * @return size of board.
	 */
	public BoardSize getSize() {
		return size;
	}

	/**
	 * Returns type of tile on given coordinates.
	 * 
	 * @param x x coordinate
	 * @param y y coordinate
	 * @return type of tile on given coordinates.
	 */
	public TileType getTileType(final int x, final int y) {
		isPositionValid(x, y);
		return board[x][y];
	}

	/**
	 * Updates type of tile to given tile type on given coordinates.
	 * 
	 * @param x    x coordinate
	 * @param y    y coordinate
	 * @param type tile type
	 */
	public void updateTileType(final int x, final int y, final TileType type) {
		isPositionValid(x, y);
		if (type == null)
			throw new IllegalArgumentException("tile type should not be null");
		board[x][y] = type;
	}

	/**
	 * This method places ship on player's board.
	 * 
	 * @param ship ship object
	 * @throws IllegalArgumentException in case it is unable to place ship
	 */
	public void placeShip(final Ship ship) {
		if (!canPlaceShip(ship))
			throw new IllegalArgumentException("can not place ship");
		for (int i = ship.getStartPosition().getX(); i <= ship.getEndPosition().getX(); i++) {
			for (int j = ship.getStartPosition().getY(); j <= ship.getEndPosition().getY(); j++) {
				board[i][j] = TileType.SHIP;
			}
		}
	}

	/**
	 * Returns true if able to place ship on player's board false otherwise.
	 * 
	 * @param ship ship object
	 * @throws IllegalArgumentException in position of ship in invalid. Position is
	 *                                  invalid if either of the coordinate is
	 *                                  negative, or greater than or equal to the
	 *                                  size of the board.
	 */
	public boolean canPlaceShip(final Ship ship) {
		isPositionValid(ship.getStartPosition().getX(), ship.getStartPosition().getY());
		for (int x = ship.getStartPosition().getX(); x <= ship.getEndPosition().getX(); x++) {
			for (int y = ship.getStartPosition().getY(); y <= ship.getEndPosition().getY(); y++) {
				if (!board[x][y].equals(TileType.WATER)) {
					return false;
				}
			}
		}
		return true;
	}

	private void isPositionValid(final int x, final int y) {
		if (x < 0 || x >= size.getValue() || y < 0 || y >= size.getValue())
			throw new IllegalArgumentException("position not valid");
	}

}
