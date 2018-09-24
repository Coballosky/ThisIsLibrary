package Comun;

import javafx.scene.control.TextArea;

public class Persona {
	private String rut;
	private String nombres;
	private String apellidos;
	
	//Constructores
	public Persona() {
		rut = null;
		nombres = null;
		apellidos = null;
	}
	public Persona(String rut, String nombres, String apellidos) {
		this.rut = rut;
		this.nombres = nombres;
		this.apellidos = apellidos;
	}
	
	//Getter & Setter
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	//Metodos
	public String infoPersona() {
		String info = "RUT: " + getRut() + "\nNombre: " + getNombres() + " " + getApellidos();
		
		return info;
	}
	
	public void mostrarInfo(TextArea texto) {
		texto.appendText(infoPersona());
	}
	
}
