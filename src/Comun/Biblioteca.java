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
		Libro buscado = buscarLibro(codTipo,codNum);
			if(buscado != null && buscado.getCode().equals(codigo)) {
				libros.remove(buscado);
				return true;
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
	
	public Usuario buscarUsuario(String rutBuscado) {
		ListIterator<Usuario> iterador = usuarios.listIterator();
		while(iterador.hasNext()) {
			Usuario usuarioItr = (Usuario)iterador.next();
			if(usuarioItr.getRut().equals(rutBuscado)) {
				return usuarioItr;
			}
			iterador.next();
		}
		return null;
	}
	
	public boolean eliminarUsuario(String rutEliminar) {
		Usuario buscado = buscarUsuario(rutEliminar);
		if(buscado != null && buscado.getNombres().equals(rutEliminar)) {
			usuarios.remove(buscado);
			return true;
		}
		return false;
	}
	
	public ArrayList<String> listaLibros(){
		ArrayList<String> listLibros = new ArrayList<String>(libros.size());
		ListIterator<Libro> itrLibros = libros.listIterator();
		while(itrLibros.hasNext()) {
			Libro actual = (Libro) itrLibros.next();
			listLibros.add(actual.infoLibro());
		}
		return listLibros;
	}
}