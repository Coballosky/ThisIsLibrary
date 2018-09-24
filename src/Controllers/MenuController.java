package Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.stage.Stage;

public class MenuController {
	@FXML private Button btnExit;
	@FXML private SplitMenuButton smbAdd;
	@FXML private MenuItem libro;
	
	public void exit(ActionEvent event) throws Exception {
		Platform.exit();
	}

	public void slLibro(ActionEvent event) {
		
	}
	
	public void agregarLibro(ActionEvent event) throws Exception{
		
		Parent root = FXMLLoader.load(getClass().getResource("/Views/addLibro.fxml"));
		Stage stage = new Stage();
		stage.setResizable(false);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
		
	}
}
