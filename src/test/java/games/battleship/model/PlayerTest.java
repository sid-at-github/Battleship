package games.battleship.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import games.battleship.constant.BoardSize;
import games.battleship.constant.PlayerId;

class PlayerTest {

	@Test
	void testGetId() {
		// case 1
		Player player1 = new Player(PlayerId.ONE, "test name 1", new Board(BoardSize.TEN));
		assertEquals(PlayerId.ONE, player1.getId());

		// case 2
		Player player2 = new Player(PlayerId.TWO, "test name 2", new Board(BoardSize.TEN));
		assertEquals(PlayerId.TWO, player2.getId());

		// case 3
		assertThrows(IllegalArgumentException.class, () -> new Player(null, "test name 3", new Board(BoardSize.TEN)));
	}

	@Test
	void testGetName() {
		// case 1
		Player player1 = new Player(PlayerId.ONE, "test name 1", new Board(BoardSize.TEN));
		assertEquals("test name 1", player1.getName());

		// case 2
		assertThrows(IllegalArgumentException.class, () -> new Player(PlayerId.ONE, null, new Board(BoardSize.TEN)));

		// case 3
		assertThrows(IllegalArgumentException.class, () -> new Player(PlayerId.ONE, "", new Board(BoardSize.TEN)));

	}

	@Test
	void testGetBoard() {
		// case 1
		Player player1 = new Player(PlayerId.ONE, "test name 1", new Board(BoardSize.TEN));
		assertEquals(BoardSize.TEN, player1.getBoard().getSize());

		// case 2
		assertThrows(IllegalArgumentException.class, () -> new Player(PlayerId.ONE, "test name 2", null));
	}

	@Test
	void testGetShips() {
		// case 1 default case
		Player player1 = new Player(PlayerId.ONE, "test name 1", new Board(BoardSize.TEN));
		assertEquals(0, player1.getShips().size());

		// case 2
		Player player2 = new Player(PlayerId.ONE, "test name 2", new Board(BoardSize.TEN));
		player2.placeShipsRandomly();
		assertEquals(5, player2.getShips().size());
	}

	@Test
	void testIsDead() {
		// case 1
		Player player1 = new Player(PlayerId.ONE, "test name 1", new Board(BoardSize.TEN));
		player1.placeShipsRandomly();
		assertTrue(!player1.isDead());

		// case 2
		Player player2 = new Player(PlayerId.ONE, "test name 2", new Board(BoardSize.TEN));
		player2.placeShipsRandomly();
		List<Ship> ships2 = player2.getShips();
		for (Ship ship : ships2) {
			for (int i = 0; i < ship.getType().getSize(); i++) {
				ship.hit();
			}
		}
		assertTrue(player2.isDead());

		// case 3
		Player player3 = new Player(PlayerId.ONE, "test name 1", new Board(BoardSize.TEN));
		player3.placeShipsRandomly();
		List<Ship> ships3 = player3.getShips();
		for (Ship ship : ships3) {
			ship.hit();
		}
		assertTrue(!player3.isDead());
	}

	@Test
	void testGetShipOnBoardPosition() {
		// case 1
		Player player1 = new Player(PlayerId.ONE, "test name 1", new Board(BoardSize.TEN));
		assertNull(player1.getShipOnBoardPosition(0, 0));

		// case 2
		Player player2 = new Player(PlayerId.ONE, "test name 2", new Board(BoardSize.TEN));
		player2.placeShipsRandomly();
		List<Ship> ships = player2.getShips();
		Random random = new Random();
		Ship randomShip = ships.get(random.nextInt(ships.size()));
		int randomX1 = random.nextInt(randomShip.getEndPosition().getX() - randomShip.getStartPosition().getX() + 1)
				+ randomShip.getStartPosition().getX();
		int randomY1 = random.nextInt(randomShip.getEndPosition().getY() - randomShip.getStartPosition().getY() + 1)
				+ randomShip.getStartPosition().getY();
		assertEquals(randomShip.getType(), player2.getShipOnBoardPosition(randomX1, randomY1).getType());
		assertEquals(randomShip.getOrientation(), player2.getShipOnBoardPosition(randomX1, randomY1).getOrientation());

		// confirming case 2
		int randomX2 = random.nextInt(randomShip.getEndPosition().getX() - randomShip.getStartPosition().getX() + 1)
				+ randomShip.getStartPosition().getX();
		int randomY2 = random.nextInt(randomShip.getEndPosition().getY() - randomShip.getStartPosition().getY() + 1)
				+ randomShip.getStartPosition().getY();
		assertEquals(randomShip.getType(), player2.getShipOnBoardPosition(randomX2, randomY2).getType());
		assertEquals(randomShip.getOrientation(), player2.getShipOnBoardPosition(randomX2, randomY2).getOrientation());
	}

	@Test
	void testPlaceShipsRandomly() {
		// case 1
		Player player1 = new Player(PlayerId.ONE, "test name 1", new Board(BoardSize.TEN));
		player1.placeShipsRandomly();
		assertEquals(5, player1.getShips().size());

		// case 2
		Player player2 = new Player(PlayerId.ONE, "test name 2", new Board(BoardSize.TEN));
		assertEquals(0, player2.getShips().size());
	}
}
