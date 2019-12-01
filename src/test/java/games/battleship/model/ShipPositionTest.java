package games.battleship.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ShipPositionTest {

	@Test
	void testGetX() {
		// case 1
		ShipPosition shipPosition1 = new ShipPosition(1, 5);
		assertEquals(1, shipPosition1.getX());

		// case 2
		assertThrows(IllegalArgumentException.class, () -> new ShipPosition(-1, 5));
	}

	@Test
	void testGetY() {
		// case 1
		ShipPosition shipPosition1 = new ShipPosition(1, 5);
		assertEquals(5, shipPosition1.getY());

		// case 2
		assertThrows(IllegalArgumentException.class, () -> new ShipPosition(1, -5));
	}
}
