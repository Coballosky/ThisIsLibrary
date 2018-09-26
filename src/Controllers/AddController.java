package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddController {

	/*  Variables correspondientes a la primera pestaña de la ventana  */
	@FXML private TextField lbl_Nombre, lbl_Apellido,lbl_Rut,lbl_Mail,lbl_Direccion;
	
	/* Variables de la segunda ventana */
	@FXML private TextField lbl_Id,lbl_Titulo,lbl_Autor,lbl_Idioma,lbl_Tema,lbl_Estado;
	
	
	public void AgregarPersona(ActionEvent event) {
		
		System.out.println(this.lbl_Nombre.getText());
				
	}
	public void AgregarLibro(ActionEvent event) {
		
		
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
