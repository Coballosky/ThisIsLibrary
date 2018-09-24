package Comun;

import java.util.Collections;
import java.util.HashMap;

public class Cubiculo {
	private int idCubiculo;
	private boolean estado;
	private HashMap<String, Integer> horarios;
	
	//Constructores
	public Cubiculo() {
		idCubiculo = 0;
		estado = false;
		horarios = (HashMap<String, Integer>) Collections.synchronizedMap(new HashMap<String, Integer>());
	}
	public Cubiculo(int id) {
		idCubiculo = id;
		estado = false;
		horarios = (HashMap<String, Integer>) Collections.synchronizedMap(new HashMap<String, Integer>());
	}
	
	//Getter & Setter
	public int getIdCubiculo() {
		return idCubiculo;
	}
	public void setIdCubiculo(int id) {
		this.idCubiculo = id;
	}
	public boolean getEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public HashMap<String, Integer> getHorarios(){
		return horarios;
	}
	public void setHorarios(HashMap<String, Integer> horario) {
		this.horarios.putAll(horario);
	}
	
	//Metodos
	
}
