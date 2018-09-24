package application;

import Comun.Libro;

public class Rental {
	private Libro libros;
	private String rutAsociado;
	private int diasRenta;
	
	
	
	//Constructores
	public Rental() {
		libros = null;
		rutAsociado = null;
		diasRenta = 0;
	}
	public Rental(int cantRentas, String rut) {
		this.libros = new Libro();
		this.rutAsociado = rut;
	}
	
	
}
