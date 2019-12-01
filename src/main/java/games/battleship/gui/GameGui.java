package games.battleship.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * 
 * The class GameGui is the game launcher. It displays the registration scene on
 * the primary stage.
 *
 */
public class GameGui extends Application {

	public static void launch(String[] args) {
		Application.launch(args);
	}

	/**
	 * This method sets the registration scene on the primary stage.
	 * 
	 * @param primaryStage primary stage
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane registrationScene = (GridPane) FXMLLoader
				.load(getClass().getClassLoader().getResource("registration.fxml"));
		Scene scene = new Scene(registrationScene);

		primaryStage.setTitle("Battleship");
		primaryStage.setResizable(false);
		primaryStage.getIcons()
				.add(new Image(getClass().getClassLoader().getResource("icons8-battleship-40.png").toExternalForm()));
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
