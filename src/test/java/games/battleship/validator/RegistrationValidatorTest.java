package games.battleship.validator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class RegistrationValidatorTest {

	@Test
	void testValidateForm() {
		// case 1
		assertTrue(RegistrationValidator.validateForm("", "player 2", "TEN").contains("player1 is required"));

		// case 2
		assertTrue(RegistrationValidator.validateForm("player 1", "", "TEN").contains("player2 is required"));

		// case 3
		assertTrue(RegistrationValidator.validateForm("player 1", "player 2", null).contains("board size is required"));

		// case 4
		assertTrue(RegistrationValidator.validateForm("player 1", "player 2", "TEN").isEmpty());
	}

}
