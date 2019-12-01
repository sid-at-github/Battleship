package games.battleship.gui;

import java.io.IOException;

import games.battleship.constant.PlayerId;
import games.battleship.constant.ShipOrientation;
import games.battleship.constant.TileType;
import games.battleship.model.Arena;
import games.battleship.model.Player;
import games.battleship.model.Ship;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

/**
 * 
 * The class TileGui is the view and controller for arena screen. It i displays
 * the registration scene on the primary stage.
 *
 */
public class TileGui extends Pane {

	private PlayerId playerId;
	private int x;
	private int y;
	private static Image waterHitImage;
	private static Image player1ShipImage;
	private static Image player2ShipImage;
	private static Image shipDestroyedImage;
	private Arena arena;

	static {
		try {
			waterHitImage = new Image(
					TileGui.class.getClassLoader().getResource("icons8-water-hit-40.jpg").openStream());
			player1ShipImage = new Image(
					TileGui.class.getClassLoader().getResource("icons8-battleship-40-1.jpg").openStream());
			player2ShipImage = new Image(
					TileGui.class.getClassLoader().getResource("icons8-battleship-40-2.jpg").openStream());
			shipDestroyedImage = new Image(
					TileGui.class.getClassLoader().getResource("icons8-ship-destroyed-40.jpg").openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * TileGui is instantiated by providing playerId, coordinates and arena object.
	 * 
	 * @param playerId player id
	 * @param x        x coordinate
	 * @param y        y coordinate
	 * @param arena    arena object
	 */
	public TileGui(PlayerId playerId, int x, int y, Arena arena) {
		super();
		this.playerId = playerId;
		this.x = x;
		this.y = y;
		this.arena = arena;
		this.setStyle("-fx-background-color: #CCCCCC;");
		fillTile();
		addMouseClickEvent();
	}

	/**
	 * This method fills tile using the arena model with water or ship.
	 * 
	 */
	public void fillTile() {
		try {
			this.setStyle("");
			Player player = arena.getPlayer(playerId);
			Ship ship = player.getShipOnBoardPosition(x, y);
			TileType type = player.getBoard().getTileType(x, y);
			if (type.equals(TileType.SHIP) && (arena.isGameOver() || !arena.isEnemy(playerId))) {
				Image shipImage = playerId.equals(PlayerId.ONE) ? player1ShipImage : player2ShipImage;
				this.setBackground(new Background(new BackgroundImage(shipImage, BackgroundRepeat.NO_REPEAT,
						BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
						new BackgroundSize(100.0, 100.0, true, true, true, false))));

				if (ship.getOrientation().equals(ShipOrientation.VERTICAL))
					this.setRotate(90);
			} else if (type.equals(TileType.WATER) && (arena.isGameOver() || !arena.isEnemy(playerId))) {
				this.setStyle("-fx-background-color: #87B2E9;");
			} else if (type.equals(TileType.SHIP_DETROYED)) {
				this.setBackground(new Background(new BackgroundImage(shipDestroyedImage, BackgroundRepeat.NO_REPEAT,
						BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
						new BackgroundSize(100.0, 100.0, true, true, true, false))));

				if (ship.getOrientation().equals(ShipOrientation.VERTICAL))
					this.setRotate(90);
			} else if (type.equals(TileType.WATER_HIT)) {
				this.setBackground(new Background(new BackgroundImage(waterHitImage, BackgroundRepeat.NO_REPEAT,
						BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
						new BackgroundSize(100.0, 100.0, true, true, true, false))));
			} else {
				this.setStyle("-fx-background-color: #CCCCCC;");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addMouseClickEvent() {
		Player player = arena.getPlayer(playerId);
		TileType type = player.getBoard().getTileType(x, y);
		boolean enable = arena.isEnemy(playerId) && !arena.isGameOver()
				&& !(type.equals(TileType.WATER_HIT) || type.equals(TileType.SHIP_DETROYED));
		if (enable)
			this.setOnMouseClicked((event) -> arena.fireMissile(x, y, arena.getTurn()));
	}
}
