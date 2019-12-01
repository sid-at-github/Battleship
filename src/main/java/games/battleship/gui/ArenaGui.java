package games.battleship.gui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import games.battleship.constant.PlayerId;
import games.battleship.dao.ResultDao;
import games.battleship.model.Arena;
import games.battleship.model.IArena;
import games.battleship.model.Result;
import games.battleship.util.RegistrationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * 
 * The class ArenaGui is the view and controller for arena screen. It is
 * responsible for handling interactions with the arena GUI.
 *
 */
public class ArenaGui implements IArena {

	private static int GRID_ROW_MIN_HEIGHT = 30;
	private static int GRID_COLUMN_MIN_WIDTH = 30;

	@FXML
	private GridPane gridPlayer1;

	@FXML
	private GridPane gridPlayer2;

	@FXML
	private TableView<Result> winnersBoard;

	@FXML
	private Label infoLabel;

	private Arena arena;

	private Map<PlayerId, GridPane> playerIdGridMap;

	private ResultDao resultDao;

	/**
	 * This method initializes arena GUI from arena object.
	 * 
	 * @param arena arena object
	 */
	public void initializeArena(Arena arena) {
		initializePlayerIdGridMap();
		arena.setArenaGui(this);
		this.arena = arena;
		this.resultDao = new ResultDao();
		drawGridHeaders();
		displayArena(true);
		addGridConstraints();
	}

	private void initializePlayerIdGridMap() {
		playerIdGridMap = new HashMap<>();
		playerIdGridMap.put(PlayerId.ONE, gridPlayer1);
		playerIdGridMap.put(PlayerId.TWO, gridPlayer2);
	}

	private void drawGridHeaders() {
		for (PlayerId playerId : PlayerId.values()) {
			for (int i = 0; i < arena.getGridSize(playerId) - 1; i++) {
				BorderPane pane = new BorderPane();
				Label header = new Label(String.valueOf(i));
				pane.setCenter(header);
				playerIdGridMap.get(playerId).add(pane, i + 1, 0);
			}
			for (char i = 1; i < arena.getGridSize(playerId); i++) {
				BorderPane pane = new BorderPane();
				Label header = new Label(String.valueOf((char) (64 + i)));
				pane.setCenter(header);
				playerIdGridMap.get(playerId).add(pane, 0, i);
			}
		}
	}

	/**
	 * This method displays arena GUI.
	 * 
	 * @param updateWinnerBoard true if updated winner board is required false
	 *                          otherwise.
	 */
	@Override
	public void displayArena(boolean updateWinnerBoard) {
		if (!arena.isGameOver()) {
			infoLabel.setText("Turn: " + arena.getPlayer(arena.getTurn()).getName());
		} else {
			infoLabel.setText(arena.getPlayer(arena.getTurn()).getName() + " Won!");
		}
		for (PlayerId playerId : PlayerId.values()) {
			for (int x = 1; x < arena.getGridSize(playerId); x++) {
				for (int y = 1; y < arena.getGridSize(playerId); y++) {
					TileGui pane = new TileGui(playerId, x - 1, y - 1, arena);
					playerIdGridMap.get(playerId).add(pane, y, x);
				}
			}

		}
		if (updateWinnerBoard)
			loadResults();
	}

	private void addGridConstraints() {
		ColumnConstraints colConstraint = new ColumnConstraints();
		colConstraint.setMinWidth(GRID_COLUMN_MIN_WIDTH);
		RowConstraints rowConstraint = new RowConstraints();
		rowConstraint.setMinHeight(GRID_ROW_MIN_HEIGHT);
		for (PlayerId playerId : PlayerId.values()) {
			for (int i = 0; i < arena.getGridSize(playerId); i++) {
				playerIdGridMap.get(playerId).getRowConstraints().add(rowConstraint);
				playerIdGridMap.get(playerId).getColumnConstraints().add(colConstraint);
			}
		}
	}

	private void loadResults() {
		List<Result> results = resultDao.getResults();
		winnersBoard.getItems().clear();
		for (Result result : results) {
			winnersBoard.getItems().add(result);
		}
	}

	@FXML
	protected void onReplayButtonClicked(ActionEvent event) {
		Arena newArena = RegistrationUtil.createModelFromInput(arena.getPlayer1().getName(),
				arena.getPlayer2().getName(), arena.getPlayer1().getBoard().getSize().toString());
		newArena.setArenaGui(this);
		this.arena = newArena;
		displayArena(true);
	}

}
