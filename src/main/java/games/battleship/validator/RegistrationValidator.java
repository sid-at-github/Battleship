package games.battleship.validator;

/**
 * 
 * The class RegistrationValidator is a helper class for validating registration
 * GUI.
 *
 */
public class RegistrationValidator {

	/**
	 * Validates if there are errors in the parameters.
	 * 
	 * @param player1   player 1 name
	 * @param player2   player 2 name
	 * @param boardSize board size
	 * @return error message.
	 */
	public static String validateForm(String player1, String player2, String boardSize) {
		String errorMessage = "";
		if (player1 == null || player1.isEmpty())
			errorMessage += "player1 is required";
		if (player2 == null || player2.isEmpty()) {
			errorMessage += (!errorMessage.isEmpty()) ? "\n" : "";
			errorMessage += "player2 is required";
		}
		if (boardSize == null || boardSize.isEmpty()) {
			errorMessage += (!errorMessage.isEmpty()) ? "\n" : "";
			errorMessage += "board size is required";
		}
		return errorMessage;
	}
}
