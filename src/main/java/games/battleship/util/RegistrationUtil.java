package games.battleship.util;

import games.battleship.constant.BoardSize;
import games.battleship.constant.PlayerId;
import games.battleship.model.Arena;
import games.battleship.model.Board;
import games.battleship.model.Player;
import games.battleship.validator.RegistrationValidator;

/**
 * The class RegistrationUtil is a helper class for creating model using GUI
 * input.
 *
 */
public class RegistrationUtil {

	/**
	 * This method creates arena object from player information and board size.
	 * 
	 * @return arena object.
	 */
	public static Arena createModelFromInput(String player1Name, String player2Name, String boardSize) {
		String errorMessage = RegistrationValidator.validateForm(player1Name, player2Name, boardSize);
		if (!errorMessage.isEmpty())
			throw new IllegalArgumentException("parameters not valid");

		Board player1Board = new Board(BoardSize.valueOf(boardSize));
		Board player2Board = new Board(BoardSize.valueOf(boardSize));
		Player player1 = new Player(PlayerId.ONE, player1Name, player1Board);
		player1.placeShipsRandomly();
		Player player2 = new Player(PlayerId.TWO, player2Name, player2Board);
		player2.placeShipsRandomly();
		Arena arena = new Arena(player1, player2);
		return arena;
	}

}
