package application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;

import ConnectionHandler.SqlConection;

/**
 * 
 * @author Nicolas
 *
 *
 * Este programa corre con base de datos MYSQL externa, para comunicar hacia tal database se requiere
 * del uso del puerto 3306 por defecto, desconosco si a la hora de entrega arreglamos ese tema cambiando el puerto 
 * o con otra solucion. porfavor tener en cuenta.
 *
 *
 */

public class Main extends Application {
	private String path = "c://POO//SqlData.txt" ;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		SetupSQL();
	

		try {
			Parent root = FXMLLoader.load(getClass().getResource("/Views/Login.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	public void closeLogin(Stage primaryStage) {
		primaryStage.close();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void SetupSQL() throws IOException {
		SqlConection SQL = new SqlConection();
		File tmpDir = new File(this.path);
		if (tmpDir.exists()) {
			SQL.LoadConData();
		}else {
			try {
			Stage ventanaSQL = new Stage();
			Parent sql = FXMLLoader.load(getClass().getResource("/Views/ConfigSQL.fxml"));
			Scene escena = new Scene(sql);
			escena.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			ventanaSQL.setScene(escena);
			ventanaSQL.showAndWait();
			}catch(IOException ex) {
			  System.out.println(ex);
			}
		}
	}
}
