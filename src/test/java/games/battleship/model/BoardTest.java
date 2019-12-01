package games.battleship.model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;

import org.junit.jupiter.api.Test;

import games.battleship.constant.BoardSize;
import games.battleship.constant.ShipOrientation;
import games.battleship.constant.ShipType;
import games.battleship.constant.TileType;

class BoardTest {

	@Test
	void testGetSize() {
		// case 1
		Board board1 = new Board(BoardSize.TEN);
		assertEquals(BoardSize.TEN, board1.getSize());

		// case 2
		Board board2 = new Board(BoardSize.TWELVE);
		assertEquals(BoardSize.TWELVE, board2.getSize());

		// case 3
		Board board3 = new Board(BoardSize.FOURTEEN);
		assertEquals(BoardSize.FOURTEEN, board3.getSize());

		// case 4
		Board board4 = new Board(BoardSize.SIXTEEN);
		assertEquals(BoardSize.SIXTEEN, board4.getSize());

		// case 5
		assertThrows(IllegalArgumentException.class, () -> new Board(null));

	}

	@Test
	void testGetTileType() {
		// case 1 and 2, default tile water
		Board board1 = new Board(BoardSize.TEN);
		Random random = new Random();
		assertEquals(TileType.WATER, board1.getTileType(random.nextInt(board1.getSize().getValue()),
				random.nextInt(board1.getSize().getValue())));

		Board board2 = new Board(BoardSize.TEN);
		assertEquals(TileType.WATER, board2.getTileType(random.nextInt(board1.getSize().getValue()),
				random.nextInt(board1.getSize().getValue())));

		// case 3
		Board board3 = new Board(BoardSize.TEN);
		board3.updateTileType(1, 1, TileType.SHIP);
		assertEquals(TileType.SHIP, board3.getTileType(1, 1));

		// case 4
		Board board4 = new Board(BoardSize.TEN);
		board4.updateTileType(1, 1, TileType.SHIP_DETROYED);
		assertEquals(TileType.SHIP_DETROYED, board4.getTileType(1, 1));

		// case 5
		Board board5 = new Board(BoardSize.TEN);
		board5.updateTileType(1, 1, TileType.WATER_HIT);
		assertEquals(TileType.WATER_HIT, board5.getTileType(1, 1));

		// case 6
		Board board6 = new Board(BoardSize.TEN);
		assertThrows(IllegalArgumentException.class, () -> board6.getTileType(-1, 5));

		// case 7
		Board board7 = new Board(BoardSize.TEN);
		assertThrows(IllegalArgumentException.class, () -> board7.getTileType(1, -5));

		// case 8
		Board board8 = new Board(BoardSize.TEN);
		assertThrows(IllegalArgumentException.class, () -> board8.getTileType(-1, -5));

		// case 9
		Board board9 = new Board(BoardSize.TEN);
		assertThrows(IllegalArgumentException.class, () -> board9.getTileType(20, 5));

		// case 10
		Board board10 = new Board(BoardSize.TEN);
		assertThrows(IllegalArgumentException.class, () -> board10.getTileType(1, 20));

		// case 11
		Board board11 = new Board(BoardSize.TEN);
		assertThrows(IllegalArgumentException.class, () -> board11.getTileType(20, 20));
	}

	@Test
	void testUpdateTileType() {
		// case 1
		Board board1 = new Board(BoardSize.TEN);
		board1.updateTileType(1, 1, TileType.SHIP);
		assertEquals(TileType.SHIP, board1.getTileType(1, 1));

		// case 2
		Board board2 = new Board(BoardSize.TEN);
		board2.updateTileType(1, 1, TileType.SHIP_DETROYED);
		assertEquals(TileType.SHIP_DETROYED, board2.getTileType(1, 1));

		// case 3
		Board board3 = new Board(BoardSize.TEN);
		board3.updateTileType(1, 1, TileType.WATER_HIT);
		assertEquals(TileType.WATER_HIT, board3.getTileType(1, 1));

		// case 4
		Board board4 = new Board(BoardSize.TEN);
		assertThrows(IllegalArgumentException.class, () -> board4.updateTileType(-1, 5, TileType.WATER_HIT));

		// case 5
		Board board5 = new Board(BoardSize.TEN);
		assertThrows(IllegalArgumentException.class, () -> board5.updateTileType(1, -5, TileType.WATER_HIT));

		// case 6
		Board board6 = new Board(BoardSize.TEN);
		assertThrows(IllegalArgumentException.class, () -> board6.updateTileType(-1, -5, TileType.WATER_HIT));

		// case 7
		Board board7 = new Board(BoardSize.TEN);
		assertThrows(IllegalArgumentException.class, () -> board7.updateTileType(20, 5, TileType.WATER_HIT));

		// case 8
		Board board8 = new Board(BoardSize.TEN);
		assertThrows(IllegalArgumentException.class, () -> board8.updateTileType(1, 20, TileType.WATER_HIT));

		// case 9
		Board board9 = new Board(BoardSize.TEN);
		assertThrows(IllegalArgumentException.class, () -> board9.updateTileType(20, 20, TileType.WATER_HIT));

		// case 10
		Board board10 = new Board(BoardSize.TEN);
		assertThrows(IllegalArgumentException.class, () -> board10.updateTileType(1, 5, null));
	}

	@Test
	void testPlaceShip() {
		// case 1
		Ship ship1 = new Ship(ShipType.BATTLESHIP, ShipOrientation.VERTICAL, new ShipPosition(0, 0));
		Board board1 = new Board(BoardSize.TEN);
		board1.placeShip(ship1);
		boolean isPlaced = true;
		for (int x = ship1.getStartPosition().getX(); x <= ship1.getEndPosition().getX(); x++) {
			for (int y = ship1.getStartPosition().getY(); y <= ship1.getEndPosition().getY(); y++) {
				if (!board1.getTileType(x, y).equals(TileType.SHIP))
					isPlaced = false;
			}
		}
		assertTrue(isPlaced);

		// case 2
		Ship ship2 = new Ship(ShipType.BATTLESHIP, ShipOrientation.VERTICAL, new ShipPosition(20, 20));
		Board board2 = new Board(BoardSize.TEN);
		assertThrows(IllegalArgumentException.class, () -> board2.placeShip(ship2));

		// case 3
		Ship ship3 = new Ship(ShipType.BATTLESHIP, ShipOrientation.VERTICAL, new ShipPosition(0, 0));
		Board board3 = new Board(BoardSize.TEN);
		board3.updateTileType(0, 0, TileType.SHIP);
		assertThrows(IllegalArgumentException.class, () -> board3.placeShip(ship3));
	}

	@Test
	void testCanPlaceShip() {
		Ship ship1 = new Ship(ShipType.BATTLESHIP, ShipOrientation.VERTICAL, new ShipPosition(0, 0));
		Board board1 = new Board(BoardSize.TEN);
		assertTrue(board1.canPlaceShip(ship1));

		// case 2
		Ship ship2 = new Ship(ShipType.BATTLESHIP, ShipOrientation.VERTICAL, new ShipPosition(20, 20));
		Board board2 = new Board(BoardSize.TEN);
		assertThrows(IllegalArgumentException.class, () -> board2.canPlaceShip(ship2));

		// case 3
		Ship ship3 = new Ship(ShipType.BATTLESHIP, ShipOrientation.VERTICAL, new ShipPosition(0, 0));
		Board board3 = new Board(BoardSize.TEN);
		board3.updateTileType(0, 0, TileType.SHIP);
		assertTrue(!board3.canPlaceShip(ship3));
	}
}
