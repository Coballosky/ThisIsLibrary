package Comun;

import java.io.File;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Setup {
private String mainPath = "c://POO",path = "c://POO//SqlData.txt";
Metodos met = new Metodos();
	
	public void SetupCarpeta() {
		boolean j = false ;
		File carpetaRoot = new File(this.mainPath);
		if (!carpetaRoot.exists()) {
		try {
		j= new File(this.mainPath).mkdir();
		}catch(Exception ex) {
			met.ShowException(ex);
		}
		if (j) {
			System.out.println("carpeta del programa creada");
		}
		}
	}
	public void SetupSql() {
		File tmpDir = new File(this.path);
		if (!tmpDir.exists()) {
			try {
				Stage ventanaSQL = new Stage();
				Parent sql = FXMLLoader.load(getClass().getResource("/Views/ConfigSQL.fxml"));
				Scene escena = new Scene(sql);
				escena.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				ventanaSQL.setScene(escena);
				ventanaSQL.showAndWait();
				}catch(Exception ex) {
				  met.ShowException(ex);
				}
		}
	}
}
