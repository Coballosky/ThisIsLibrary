package Comun;

import java.util.ArrayList;
import java.util.Collections;

public class Usuario extends Persona {
	private String mail;
	private ArrayList<Rental> rentas;
	
	//Constructores
	public Usuario() {
		super();
		mail = null;
		rentas = (ArrayList<Rental>) Collections.synchronizedList(new ArrayList<Rental>());
	}
	
	public Usuario(String rut, String nombre, String apellido, String mail) {
		super(rut,nombre,apellido);
		this.mail = mail;
		rentas = (ArrayList<Rental>) Collections.synchronizedList(new ArrayList<Rental>());
	}
	
	//Getter & Setter
	//Incluye los metodos de la superclase Persona
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public ArrayList<Rental> getRentas(){
		return rentas;
	}
	
}
