package Comun;

import javafx.scene.control.TextArea;

public class Libro {
	private final String codigoLibro;
	private String titulo;
	private String autor;
	private String tema;
	private String idioma;
	private String estado;
	private boolean rentado;
	
	//Constructores
	public Libro(String codigo) {
		codigoLibro = codigo;
		titulo = null;
		autor = null;
		tema = null;
		idioma = null;
		estado = null;
		rentado = false;
	}
	/*Necesita tener un codigo valido y especifico para no generar problemas al ser variable final*/
	
	public Libro(String codigo, String nombreLibro, String autor, String tema, String idioma, String estado) {
		this.codigoLibro = codigo;
		this.titulo = nombreLibro;
		this.autor = autor;
		this.idioma = idioma;
		this.tema = tema;
		this.estado = estado;
		this.rentado = false;
	}
	
	//Getter & Setter
	public String getCode() {
		return codigoLibro;
	}
	/*Al ser variable final, codigoLibro no es modificable a traves de metodos
	 * por lo que no tiene metodo setter, se mantiene con el valor con el que es
	 * inicializado en el constructor*/
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public String getTema() {
		return tema;
	}
	public void setTema(String tema) {
		this.tema = tema;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public boolean getRentado() {
		return rentado;
	}
	public void setRentado(boolean rentado) {
		this.rentado = rentado;
	}
	
	//Metodos
	public String infoLibro() {
		String info = getTema() + " " + getCode() + "|" + getTitulo() + ", " + getAutor() + "\n\tIdioma: " 
				+ getIdioma() + ", Estado: " + getEstado() + ", Rentado: ";
		
		if(getRentado() == true) {
			info = info + "SI";
		}
		else {
			info = info + "NO";
		}
		
		return info;
	}
	
	public void mostrarInfo(TextArea texto) {
		texto.appendText(infoLibro()+"\n\n");
	}
	
	//Metodos de modificacion
	public void modificarLibro(String campo, Object dato) {
		if(campo.equals("titulo")) {
			this.setTitulo((String)dato);
		}
		else if(campo.equals("autor")) {
			this.setAutor((String)dato);
		}
		else if(campo.equals("tema")) {
			this.setTema((String)dato);
		}
		else if(campo.equals("estado")) {
			this.setEstado((String)dato);
		}
		else if(campo.equals("rentado")) {
			this.setRentado((boolean) dato);
		}
	}
	
}