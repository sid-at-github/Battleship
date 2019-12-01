package games.battleship.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import games.battleship.constant.ShipOrientation;
import games.battleship.constant.ShipType;

class ShipTest {

	@Test
	void testGetType() {
		// case 1
		Ship ship1 = new Ship(ShipType.BATTLESHIP, ShipOrientation.VERTICAL, new ShipPosition(0, 0));
		assertEquals(ShipType.BATTLESHIP, ship1.getType());

		// case 2
		assertThrows(IllegalArgumentException.class,
				() -> new Ship(null, ShipOrientation.VERTICAL, new ShipPosition(0, 0)));
	}

	@Test
	void testGetOrientation() {
		// case 1
		Ship ship1 = new Ship(ShipType.BATTLESHIP, ShipOrientation.VERTICAL, new ShipPosition(0, 0));
		assertEquals(ShipOrientation.VERTICAL, ship1.getOrientation());

		// case 2
		assertThrows(IllegalArgumentException.class, () -> new Ship(ShipType.BATTLESHIP, null, new ShipPosition(0, 0)));
	}

	@Test
	void testGetStartPosition() {
		// case 1
		Ship ship1 = new Ship(ShipType.BATTLESHIP, ShipOrientation.VERTICAL, new ShipPosition(0, 1));
		assertEquals(0, ship1.getStartPosition().getX());
		assertEquals(1, ship1.getStartPosition().getY());

		// case 2
		assertThrows(IllegalArgumentException.class,
				() -> new Ship(ShipType.BATTLESHIP, ShipOrientation.VERTICAL, null));
	}

	@Test
	void testGetEndPosition() {
		// case 1
		Ship ship1 = new Ship(ShipType.BATTLESHIP, ShipOrientation.VERTICAL, new ShipPosition(0, 1));
		assertEquals(3, ship1.getEndPosition().getX());
		assertEquals(1, ship1.getEndPosition().getY());

		// case 2
		Ship ship2 = new Ship(ShipType.BATTLESHIP, ShipOrientation.HORIZONTAL, new ShipPosition(0, 1));
		assertEquals(0, ship2.getEndPosition().getX());
		assertEquals(4, ship2.getEndPosition().getY());
	}

	@Test
	void testIsPresentOnPosition() {
		// case 1
		Ship ship1 = new Ship(ShipType.BATTLESHIP, ShipOrientation.VERTICAL, new ShipPosition(0, 1));
		assertTrue(ship1.isPresentOnPosition(0, 1));
		assertTrue(ship1.isPresentOnPosition(1, 1));
		assertTrue(ship1.isPresentOnPosition(2, 1));
		assertTrue(ship1.isPresentOnPosition(3, 1));
		assertTrue(!ship1.isPresentOnPosition(4, 1));
		assertTrue(!ship1.isPresentOnPosition(0, 2));
	}

	@Test
	void testIsDestroyed() {
		// case 1
		Ship ship1 = new Ship(ShipType.BATTLESHIP, ShipOrientation.VERTICAL, new ShipPosition(0, 0));
		ship1.hit();
		assertTrue(!ship1.isDestroyed());
		ship1.hit();
		assertTrue(!ship1.isDestroyed());
		ship1.hit();
		assertTrue(!ship1.isDestroyed());
		ship1.hit();
		assertTrue(ship1.isDestroyed());

		// case 2
		Ship ship2 = new Ship(ShipType.DESTROYER, ShipOrientation.VERTICAL, new ShipPosition(0, 0));
		ship2.hit();
		assertTrue(!ship2.isDestroyed());
		ship2.hit();
		assertTrue(!ship2.isDestroyed());
		ship2.hit();
		assertTrue(ship2.isDestroyed());
	}
}
