package games.battleship.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import games.battleship.constant.BoardSize;
import games.battleship.model.Arena;

class RegistrationUtilTest {

	@Test
	void testCreateModelFromInput() {
		// case 1
		Arena arena1 = RegistrationUtil.createModelFromInput("player 1", "player 2", "TEN");
		assertEquals("player 1", arena1.getPlayer1().getName());
		assertEquals("player 2", arena1.getPlayer2().getName());
		assertEquals(BoardSize.TEN, arena1.getPlayer1().getBoard().getSize());
		assertEquals(BoardSize.TEN, arena1.getPlayer2().getBoard().getSize());

		// case 1
		Arena arena2 = RegistrationUtil.createModelFromInput("player 1", "player 2", "TWELVE");
		assertEquals("player 1", arena2.getPlayer1().getName());
		assertEquals("player 2", arena2.getPlayer2().getName());
		assertEquals(BoardSize.TWELVE, arena2.getPlayer1().getBoard().getSize());
		assertEquals(BoardSize.TWELVE, arena2.getPlayer2().getBoard().getSize());

		// case 2
		assertThrows(IllegalArgumentException.class,
				() -> RegistrationUtil.createModelFromInput("player 1", "player 2", null));

	}

}
