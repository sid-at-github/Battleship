package games.battleship.gui;

import java.io.IOException;

import games.battleship.model.Arena;
import games.battleship.util.RegistrationUtil;
import games.battleship.validator.RegistrationValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 
 * The class RegistrationGui handles interactions with the registration GUI. It
 * is also responsible for displaying the arena scene on the primary stage when
 * a player clicks on the start button.
 *
 */
public class RegistrationGui {

	@FXML
	private TextField player1Name;

	@FXML
	private TextField player2Name;

	@FXML
	private Text errorMessage;

	@FXML
	private ComboBox<String> boardSize;

	@FXML
	protected void handleStartAction(ActionEvent event) throws IOException {
		String response = RegistrationValidator.validateForm(player1Name.getText(), player2Name.getText(),
				boardSize.getValue());
		if (!response.isEmpty())
			errorMessage.setText(response);
		else {
			Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("arena.fxml"));
			BorderPane player1Arena = loader.load();
			ArenaGui controller = loader.getController();
			Arena arena = RegistrationUtil.createModelFromInput(player1Name.getText(), player2Name.getText(),
					boardSize.getValue());
			controller.initializeArena(arena);
			player1Arena.getStylesheets().add(getClass().getClassLoader().getResource("arena.css").toExternalForm());
			Scene nextScene = new Scene(player1Arena);
			primaryStage.setScene(nextScene);

		}
	}
}
