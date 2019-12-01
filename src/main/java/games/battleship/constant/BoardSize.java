package games.battleship.constant;

/**
 * 
 * The enum BoardSize contains the permitted board size.
 *
 */
public enum BoardSize {

	TEN(10), TWELVE(12), FOURTEEN(14), SIXTEEN(16);

	private final int value;

	BoardSize(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
