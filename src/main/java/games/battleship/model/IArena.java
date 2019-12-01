package games.battleship.model;

/**
 * The interface IArena defines the contract for displaying arena GUI.
 */
public interface IArena {

	/**
	 * Displays arena GUI.
	 * 
	 * @param updateWinnerBoard true if Winner Board needs to be updated false
	 *                          otherwise.
	 */
	public void displayArena(boolean updateWinnerBoard);

}
