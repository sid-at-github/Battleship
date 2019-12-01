package games.battleship.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import games.battleship.constant.BoardSize;
import games.battleship.constant.PlayerId;
import games.battleship.constant.ShipOrientation;
import games.battleship.constant.ShipType;
import games.battleship.dao.ResultDao;

class ArenaTest {

	@Test
	void testGetPlayer1() {
		// case 1
		Player player2Case1 = new Player(PlayerId.TWO, "test player2", new Board(BoardSize.TEN));
		assertThrows(IllegalArgumentException.class, () -> new Arena(null, player2Case1));

		// case 2
		Player player1Case2 = new Player(PlayerId.ONE, "test player1", new Board(BoardSize.TEN));
		Player player2Case2 = new Player(PlayerId.TWO, "test player2", new Board(BoardSize.TEN));
		Arena arena2 = new Arena(player1Case2, player2Case2);
		assertEquals(player1Case2.getId(), arena2.getPlayer1().getId());
		assertEquals(player1Case2.getName(), arena2.getPlayer1().getName());
		assertEquals(player1Case2.getBoard().getSize(), arena2.getPlayer1().getBoard().getSize());
	}

	@Test
	void testGetPlayer2() {
		// case 1
		Player player1Case1 = new Player(PlayerId.ONE, "test player1", new Board(BoardSize.TEN));
		assertThrows(IllegalArgumentException.class, () -> new Arena(player1Case1, null));

		// case 2
		Player player1Case2 = new Player(PlayerId.ONE, "test player1", new Board(BoardSize.TEN));
		Player player2Case2 = new Player(PlayerId.TWO, "test player2", new Board(BoardSize.TEN));
		Arena arena2 = new Arena(player1Case2, player2Case2);
		assertEquals(player2Case2.getId(), arena2.getPlayer2().getId());
		assertEquals(player2Case2.getName(), arena2.getPlayer2().getName());
		assertEquals(player2Case2.getBoard().getSize(), arena2.getPlayer2().getBoard().getSize());
	}

	@Test
	void testGetOtherPlayer() {
		// case 1 default case
		Player player1Case1 = new Player(PlayerId.ONE, "test player1", new Board(BoardSize.TEN));
		Player player2Case1 = new Player(PlayerId.TWO, "test player2", new Board(BoardSize.TEN));
		Arena arena1 = new Arena(player1Case1, player2Case1);
		assertEquals(player2Case1.getId(), arena1.getOtherPlayer().getId());
		assertEquals(player2Case1.getName(), arena1.getOtherPlayer().getName());
		assertEquals(player2Case1.getBoard().getSize(), arena1.getOtherPlayer().getBoard().getSize());

		// case 2
		Player player1Case2 = new Player(PlayerId.ONE, "test player1", new Board(BoardSize.TEN));
		Player player2Case2 = new Player(PlayerId.TWO, "test player2", new Board(BoardSize.TEN));
		Arena arena2 = new Arena(player1Case2, player2Case2);
		arena2.toggleTurn();
		assertEquals(player1Case2.getId(), arena2.getOtherPlayer().getId());
		assertEquals(player1Case2.getName(), arena2.getOtherPlayer().getName());
		assertEquals(player1Case2.getBoard().getSize(), arena2.getOtherPlayer().getBoard().getSize());

		// confirming case 2
		arena2.toggleTurn();
		assertEquals(player2Case2.getId(), arena2.getOtherPlayer().getId());
		assertEquals(player2Case2.getName(), arena2.getOtherPlayer().getName());
		assertEquals(player2Case2.getBoard().getSize(), arena2.getOtherPlayer().getBoard().getSize());
	}

	@Test
	void testGetGridSize() {
		// case 1
		Player player1Case1 = new Player(PlayerId.ONE, "test player1", new Board(BoardSize.TEN));
		Player player2Case1 = new Player(PlayerId.TWO, "test player2", new Board(BoardSize.TEN));
		Arena arena1 = new Arena(player1Case1, player2Case1);
		assertEquals(BoardSize.TEN.getValue() + 1, arena1.getGridSize(player1Case1.getId()));
		assertEquals(BoardSize.TEN.getValue() + 1, arena1.getGridSize(player2Case1.getId()));

		// case 2
		Player player1Case2 = new Player(PlayerId.ONE, "test player1", new Board(BoardSize.TWELVE));
		Player player2Case2 = new Player(PlayerId.TWO, "test player2", new Board(BoardSize.TWELVE));
		Arena arena2 = new Arena(player1Case2, player2Case2);
		assertEquals(BoardSize.TWELVE.getValue() + 1, arena2.getGridSize(player1Case2.getId()));
		assertEquals(BoardSize.TWELVE.getValue() + 1, arena2.getGridSize(player2Case2.getId()));

		// case 3
		Player player1Case3 = new Player(PlayerId.ONE, "test player1", new Board(BoardSize.FOURTEEN));
		Player player2Case3 = new Player(PlayerId.TWO, "test player2", new Board(BoardSize.FOURTEEN));
		Arena arena3 = new Arena(player1Case3, player2Case3);
		assertEquals(BoardSize.FOURTEEN.getValue() + 1, arena3.getGridSize(player1Case3.getId()));
		assertEquals(BoardSize.FOURTEEN.getValue() + 1, arena3.getGridSize(player2Case3.getId()));

		// case 4
		Player player1Case4 = new Player(PlayerId.ONE, "test player1", new Board(BoardSize.SIXTEEN));
		Player player2Case4 = new Player(PlayerId.TWO, "test player2", new Board(BoardSize.SIXTEEN));
		Arena arena4 = new Arena(player1Case4, player2Case4);
		assertEquals(BoardSize.SIXTEEN.getValue() + 1, arena4.getGridSize(player1Case4.getId()));
		assertEquals(BoardSize.SIXTEEN.getValue() + 1, arena4.getGridSize(player2Case4.getId()));
	}

	@Test
	void testIsEnemy() {
		// case 1 default case
		Player player1Case1 = new Player(PlayerId.ONE, "test player1", new Board(BoardSize.TEN));
		Player player2Case1 = new Player(PlayerId.TWO, "test player2", new Board(BoardSize.TEN));
		Arena arena1 = new Arena(player1Case1, player2Case1);
		assertTrue(!arena1.isEnemy(player1Case1.getId()));
		assertTrue(arena1.isEnemy(player2Case1.getId()));

		// case 2
		Player player1Case2 = new Player(PlayerId.ONE, "test player1", new Board(BoardSize.TWELVE));
		Player player2Case2 = new Player(PlayerId.TWO, "test player2", new Board(BoardSize.TWELVE));
		Arena arena2 = new Arena(player1Case2, player2Case2);
		arena2.toggleTurn();
		assertTrue(arena2.isEnemy(player1Case1.getId()));
		assertTrue(!arena2.isEnemy(player2Case1.getId()));

		// confirming case 2
		arena2.toggleTurn();
		assertTrue(!arena2.isEnemy(player1Case1.getId()));
		assertTrue(arena2.isEnemy(player2Case1.getId()));
	}

	@Test
	void testToggleTurn() {
		// case 1
		Player player1Case1 = new Player(PlayerId.ONE, "test player1", new Board(BoardSize.TEN));
		Player player2Case1 = new Player(PlayerId.TWO, "test player2", new Board(BoardSize.TEN));
		Arena arena1 = new Arena(player1Case1, player2Case1);
		arena1.toggleTurn();
		assertEquals(player2Case1.getId(), arena1.getTurn());

		// confirming case 1
		arena1.toggleTurn();
		assertEquals(player1Case1.getId(), arena1.getTurn());
	}

	@Test
	void testSetTurnToDefault() {
		// case 1
		Player player1Case1 = new Player(PlayerId.ONE, "test player1", new Board(BoardSize.TEN));
		Player player2Case1 = new Player(PlayerId.TWO, "test player2", new Board(BoardSize.TEN));
		Arena arena1 = new Arena(player1Case1, player2Case1);
		arena1.setTurnToDefault();
		assertEquals(player1Case1.getId(), arena1.getTurn());

		// confirming case 1
		arena1.toggleTurn();
		arena1.setTurnToDefault();
		assertEquals(player1Case1.getId(), arena1.getTurn());
	}

	@Test
	void testIsGameOver() {
		// case 1 default case
		Player player1Case1 = new Player(PlayerId.ONE, "test player1", new Board(BoardSize.TEN));
		Player player2Case1 = new Player(PlayerId.TWO, "test player2", new Board(BoardSize.TEN));
		Arena arena1 = new Arena(player1Case1, player2Case1);
		assertTrue(!arena1.isGameOver());

		// case 2
		Player player1Case2 = new Player(PlayerId.ONE, "test player1", new Board(BoardSize.TWELVE));
		List<Ship> player1Case2Ships = new ArrayList<>();
		player1Case2Ships.add(new Ship(ShipType.PATROL_BOAT, ShipOrientation.HORIZONTAL, new ShipPosition(0, 0)));
		player1Case2.setShips(player1Case2Ships);
		player1Case2.getBoard().placeShip(player1Case2Ships.get(0));
		Player player2Case2 = new Player(PlayerId.TWO, "test player2", new Board(BoardSize.TWELVE));
		List<Ship> player2Case2Ships = new ArrayList<>();
		player2Case2Ships.add(new Ship(ShipType.PATROL_BOAT, ShipOrientation.HORIZONTAL, new ShipPosition(0, 0)));
		player2Case2.getBoard().placeShip(player2Case2Ships.get(0));
		player2Case2.setShips(player2Case2Ships);
		Arena arena2 = new Arena(player1Case2, player2Case2);
		arena2.fireMissile(0, 0, PlayerId.ONE);
		arena2.fireMissile(1, 1, PlayerId.TWO);
		arena2.fireMissile(0, 1, PlayerId.ONE);
		assertTrue(arena2.isGameOver());
	}

	@Test
	void testFireMissile() {
		// case 1 wrong player trying to fire missile
		Player player1Case1 = new Player(PlayerId.ONE, "test player1", new Board(BoardSize.TEN));
		player1Case1.placeShipsRandomly();
		Player player2Case1 = new Player(PlayerId.TWO, "test player2", new Board(BoardSize.TEN));
		player2Case1.placeShipsRandomly();
		Arena arena1 = new Arena(player1Case1, player2Case1);
		assertThrows(IllegalArgumentException.class, () -> arena1.fireMissile(0, 0, player2Case1.getId()));

		// case 2 ship is hit
		Player player1Case2 = new Player(PlayerId.ONE, "test player1", new Board(BoardSize.TWELVE));
		player1Case2.placeShipsRandomly();
		Player player2Case2 = new Player(PlayerId.TWO, "test player2", new Board(BoardSize.TWELVE));
		player2Case2.placeShipsRandomly();
		List<Ship> ships = player2Case2.getShips();
		Random random = new Random();
		Ship randomShip = ships.get(random.nextInt(ships.size()));
		Arena arena2 = new Arena(player1Case2, player2Case2);
		boolean hitCase2 = arena2.fireMissile(randomShip.getStartPosition().getX(),
				randomShip.getStartPosition().getY(), player1Case2.getId());
		assertTrue(hitCase2);

		// case 3 ship is not hit
		Player player1Case3 = new Player(PlayerId.ONE, "test player1", new Board(BoardSize.FOURTEEN));
		Player player2Case3 = new Player(PlayerId.TWO, "test player2", new Board(BoardSize.FOURTEEN));
		Arena arena3 = new Arena(player1Case3, player2Case3);
		boolean hitCase3 = arena3.fireMissile(random.nextInt(player2Case3.getBoard().getSize().getValue()),
				random.nextInt(player2Case3.getBoard().getSize().getValue()), player1Case2.getId());
		assertTrue(!hitCase3);

		// case 4
		Player player1Case4 = new Player(PlayerId.ONE, "test player1", new Board(BoardSize.FOURTEEN));
		Player player2Case4 = new Player(PlayerId.TWO, "test player2", new Board(BoardSize.FOURTEEN));
		Arena arena4 = new Arena(player1Case4, player2Case4);
		int randomXCoordinate = random.nextInt(player2Case4.getBoard().getSize().getValue());
		int randomYCoordinate = random.nextInt(player2Case4.getBoard().getSize().getValue());
		arena4.fireMissile(randomXCoordinate, randomYCoordinate, player1Case2.getId());
		assertThrows(IllegalArgumentException.class,
				() -> arena4.fireMissile(randomXCoordinate, randomYCoordinate, player1Case2.getId()));
	}

	@AfterAll
	static void cleanup() {
		ResultDao resultDao = new ResultDao();
		resultDao.deleteResult("test player1", "test player2");
	}

}
