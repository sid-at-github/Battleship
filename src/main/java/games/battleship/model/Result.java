package games.battleship.model;

/**
 * The class result represents the result of the game.
 */
public class Result {

	private final String player1;
	private final String player2;
	private final String winner;

	/**
	 * Result is instantiated by providing player 1 name, player 2 name and winner
	 * name.
	 * 
	 * @param player1 player 1 name
	 * @param player2 player 2 name
	 * @param winner  winner name
	 * @throws IllegalArgumentException if any of the parameters are null or empty
	 */
	public Result(final String player1, final String player2, final String winner) {
		if (player1 == null || player1.isEmpty() || player2 == null || player2.isEmpty() || winner == null
				|| winner.isEmpty())
			throw new IllegalArgumentException(
					"unable to construct result none of the parameters can be null or empty");
		this.player1 = player1;
		this.player2 = player2;
		this.winner = winner;
	}

	/**
	 * Returns player 1 name.
	 * 
	 * @return player 1 name.
	 */
	public String getPlayer1() {
		return player1;
	}

	/**
	 * Returns player 2 name.
	 * 
	 * @return player 2 name.
	 */
	public String getPlayer2() {
		return player2;
	}

	/**
	 * Returns winner name.
	 * 
	 * @return winner name.
	 */
	public String getWinner() {
		return winner;
	}
}
