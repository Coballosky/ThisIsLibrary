package Comun;

public class Rental {
	private Libro libro;
	private String rutAsociado;
	private int diasRenta;
	
	//Constructores
	public Rental(Libro libro) {
		this.libro = libro;
		rutAsociado = null;
		diasRenta = 0;
	}
	public Rental(Libro libro,String rut) {
		this.libro = libro;
		rutAsociado = rut;
		diasRenta = 0;
	}
	
	//Getter & Setter
	public int getDiasRenta() {
		return diasRenta;
	}
	public void setDiasRenta(int dias) {
		this.diasRenta = dias;
	}
	public Libro getLibro() {
		return libro;
	}
	public void setLibro(Libro libroRentado) {
		this.libro = libroRentado; //For same object reference
	}
	public String getRut() {
		return rutAsociado;
	}
	public void setRut(String rut) {
		this.rutAsociado = rut;
	}
}