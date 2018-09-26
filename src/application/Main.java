package application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import Comun.Metodos;
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
	private String path = "c://POO//SqlData.txt" ,path_Persona = "c://POO//Persona.txt";
	Metodos met = new Metodos();
	
	@Override
	public void start(Stage primaryStage) throws IOException, SQLException {
		SetupSQL();			// se verifica que se hayan ingresado los datos de la sql anteriormente, sino se piden
		DescargaSQL();		// se descarga la base de datos para hacer uso de archivos ( requerimiento entrega 1 )
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/Views/Login.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			} catch(Exception ex) {
				met.ShowException(ex);
			}
	}
	
	public void closeLogin(Stage primaryStage) {
		primaryStage.close();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	public void DescargaSQL() {
		SqlConection SQL = new SqlConection();
		File Per = new File(this.path_Persona);
		if (Per.exists() ) {
			try {
				SQL.DescargarPersonas();
			} catch (SQLException | IOException ex) {
				met.ShowException(ex);
			}
		}else {
			System.out.println("No se encontro el archivo");
		}
	}
	
	
	public void SetupSQL() {
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
