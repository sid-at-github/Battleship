package games.battleship.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ResultTest {

	@Test
	void testGetPlayer1() {
		// case 1
		Result result1 = new Result("player 1", "player 2", "player 1");
		assertEquals("player 1", result1.getPlayer1());

		// case 2
		assertThrows(IllegalArgumentException.class, () -> new Result(null, "player 2", "player 1"));
	}

	@Test
	void testGetPlayer2() {
		// case 1
		Result result1 = new Result("player 1", "player 2", "player 1");
		assertEquals("player 2", result1.getPlayer2());

		// case 2
		assertThrows(IllegalArgumentException.class, () -> new Result("player 1", null, "player 1"));
	}

	@Test
	void testGetWinner() {
		// case 1
		Result result1 = new Result("player 1", "player 2", "player 1");
		assertEquals("player 1", result1.getWinner());

		// case 2
		assertThrows(IllegalArgumentException.class, () -> new Result("player 1", "player 2", null));
	}

}
