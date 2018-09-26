package Comun;
import java.util.ArrayList;
import java.util.ListIterator;

public class Biblioteca {
	private ArrayList<Libro> libros;
	private ArrayList<Usuario> usuarios;
	private ArrayList<Cubiculo> cubiculos;
	
	//Constructores
	public Biblioteca(ArrayList<Libro> libros, ArrayList<Cubiculo> cubiculos) {
		this.libros = libros;
		this.cubiculos = cubiculos;
		this.usuarios = null;
	}

	//Getter & Setter
	public void setLibros(ArrayList<Libro> listaLibros) {
		this.libros = listaLibros;
	}
	public ArrayList<Libro> getListaLibros(){
		return libros;
	}
	public void setUsuarios(ArrayList<Usuario> listaUsuarios) {
		this.usuarios = listaUsuarios;
	}
	public ArrayList<Usuario> getUsuarios(){
		return usuarios;
	}
	public void setCubiculos(ArrayList<Cubiculo> cubiculos) {
		this.cubiculos = cubiculos;
	}
	public ArrayList<Cubiculo> getCubiculos(){
		return cubiculos;
	}
	
	//Metodos
	public Libro buscarLibro(String nombreLibro) {	//Busca libro, cualquier copia
		ListIterator<Libro> iterador = libros.listIterator();
		while(iterador.hasNext()) {
			Libro libroItr = (Libro)iterador.next();
			if(libroItr.getTitulo().equals(nombreLibro)) {
				return libroItr;
			}
		}
		
		return null;
	}
	
	public Libro buscarLibro(String codTipo, int codNum) {
		String codigo = codTipo + Integer.toString(codNum);
		ListIterator<Libro> iterador = libros.listIterator();
		while(iterador.hasNext()) {
			Libro libroItr = (Libro)iterador.next();
			if(libroItr.getCode().equals(codigo)) {
				return libroItr;
			}
		}
		
		return null;
	}
	
	public boolean agregarLibro(Libro nuevoLibro) {
		if(buscarLibro(nuevoLibro.getTitulo()) == null) {
			libros.add(nuevoLibro);
			return true;
		}
		return false;
	}
	
	public boolean eliminarLibro(String codTipo, int codNum) {	//Elimina libro especifico
		String codigo = codTipo + Integer.toString(codNum);
		ListIterator<Libro> iterador = libros.listIterator();
		while(iterador.hasNext()) {
			Libro libroItr = (Libro)iterador.next();
			if(libroItr.getCode().equals(codigo)) {
				iterador.remove();;
				return true;
			}
		}
		
		return false;
	}
	
	public boolean agregarUsuario(Usuario nuevoUsuario) {
		if(buscarUsuario(nuevoUsuario.getRut()) == null) {
			usuarios.add(nuevoUsuario);
			return true;
		}
		return false;
	}
	
}