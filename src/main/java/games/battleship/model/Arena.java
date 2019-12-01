package games.battleship.model;

import games.battleship.constant.PlayerId;
import games.battleship.constant.TileType;
import games.battleship.dao.ResultDao;

/**
 * The class Arena contains the battle area of both players. It has information
 * about both players so a player can fire a missile using this class by
 * providing its identifier and the coordinates of the target location. This
 * class also contains information about the state of the game i.e, whether the
 * game is over or not and which player turn it is.
 * 
 */
public class Arena {

	private final Player player1;
	private final Player player2;
	private IArena arenaGui;
	private PlayerId turn;
	private boolean isGameOver = false;
	private ResultDao playerDao;

	/**
	 * Arena is instantiated by providing both the players.
	 * 
	 * @param player1 Player1 object
	 * @param player2 Player2 object
	 * @throws IllegalArgumentException if either of the player is null
	 */
	public Arena(final Player player1, final Player player2) {
		if (player1 == null || player2 == null)
			throw new IllegalArgumentException("no player can be null");
		this.player1 = player1;
		this.player2 = player2;
		setTurnToDefault();
		playerDao = new ResultDao();
	}

	/**
	 * Returns player1.
	 * 
	 * @return player1 object.
	 */
	public Player getPlayer1() {
		return player1;
	}

	/**
	 * Returns player2.
	 * 
	 * @return player2 object.
	 */
	public Player getPlayer2() {
		return player2;
	}

	/**
	 * Returns the enemy in the arena.
	 * 
	 * @return the player that is the enemy in the arena.
	 */
	public Player getOtherPlayer() {
		if (turn == player1.getId()) {
			return player2;
		}
		return player1;
	}

	/**
	 * Returns the player whose id matches with the given id.
	 * 
	 * @param playerId PlayerId of the player whose object is required.
	 * @return the player with the given id.
	 */
	public Player getPlayer(PlayerId playerId) {
		if (playerId == player1.getId()) {
			return player1;
		}
		return player2;
	}

	/**
	 * Returns the player whose turn it is.
	 * 
	 * @return the player whose turn it is.
	 */
	public PlayerId getTurn() {
		return turn;
	}

	/**
	 * Sets the arena GUI object that implements IArena interface.
	 * 
	 * @param arenaGui Arena GUI object.
	 */
	public void setArenaGui(IArena arenaGui) {
		this.arenaGui = arenaGui;
	}

	/**
	 * Returns the grid size of the arena.
	 * 
	 * @param playerId PlayerId of the player whose grid size is required.
	 * @return the grid size of the arena.
	 */
	public int getGridSize(PlayerId playerId) {
		if (playerId.equals(player1.getId())) {
			return player1.getBoard().getSize().getValue() + 1;
		}
		return player2.getBoard().getSize().getValue() + 1;
	}

	/**
	 * Returns true if given player is the enemy false otherwise.
	 * 
	 * @param playerId PlayerId of the player
	 * @return true if given player is the enemy false otherwise.
	 */
	public boolean isEnemy(PlayerId playerId) {
		return turn != playerId;
	}

	/**
	 * This method switches turn to other player.
	 * 
	 */
	public void toggleTurn() {
		turn = getOtherPlayer().getId();
	}

	/**
	 * Sets turn to default.
	 * 
	 */
	public void setTurnToDefault() {
		turn = PlayerId.ONE;
	}

	/**
	 * Returns true if game is over false otherwise.
	 * 
	 * @return true if game is over false otherwise.
	 */
	public boolean isGameOver() {
		return isGameOver;
	}

	/**
	 * This method implements the core logic of the game. A player can call this
	 * method on enemy's coordinates to fire a missile. This method will update the
	 * arena GUI accordingly if the missile hit the ship or water. This method will
	 * update the game state in case the game is over. Also, this method will store
	 * results in persistent storage and update the winner score board.
	 * 
	 * @param enemyX         enemy x coordinate
	 * @param enemyY         enemy y coordinate
	 * @param missileFiredBy player id of the player who is firing the missile
	 * @throws IllegalArgumentException in case player is trying to fire missile
	 *                                  even if its not its turn or player is trying
	 *                                  to fire on coordinate it has fired before.
	 * @return true if missile hits the ship of enemy false otherwise.
	 */
	public boolean fireMissile(int enemyX, int enemyY, PlayerId missileFiredBy) {
		if (!missileFiredBy.equals(turn))
			throw new IllegalArgumentException("not your turn");
		boolean hit = false;
		Player enemy = getOtherPlayer();
		Board board = enemy.getBoard();
		TileType tileType = board.getTileType(enemyX, enemyY);
		if (tileType.equals(TileType.WATER_HIT) || tileType.equals(TileType.SHIP_DETROYED))
			throw new IllegalArgumentException("already fired missile on this coordinate");
		if (tileType.equals(TileType.WATER)) {
			board.updateTileType(enemyX, enemyY, TileType.WATER_HIT);
		} else if (tileType.equals(TileType.SHIP)) {
			hit = true;
			board.updateTileType(enemyX, enemyY, TileType.SHIP_DETROYED);
			Ship ship = enemy.getShipOnBoardPosition(enemyX, enemyY);
			if (ship != null)
				ship.hit();
		}
		if (!enemy.isDead()) {
			toggleTurn();
			if (arenaGui != null)
				arenaGui.displayArena(false);
		} else {
			playerDao.saveResult(player1.getName(), player2.getName(), getPlayer(getTurn()).getName());
			isGameOver = true;
			if (arenaGui != null)
				arenaGui.displayArena(true);
		}
		return hit;
	}
}
