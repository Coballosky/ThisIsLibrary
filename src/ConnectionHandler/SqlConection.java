package ConnectionHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import Comun.Metodos;

/**
 * @author Nicolas
 *	Esta clase fue pensada con un administrador de conecciones amigable en cuanto a los nombres
 *	un lugar unico en caso de requerir cambiar la direccion del sql clave y usuario
 *
 **/


public class SqlConection {
	Metodos met = new Metodos();
	private String url,username,password;	
	private Connection con;
	public Statement st;
	private String dbPersonas = "Persona";
	private boolean debug = true;
	
	public void LoadConData() throws IOException {
		System.out.println("Cargando sql data");
		String fileName = "c://POO//SqlData.txt";
		FileReader fileReader =   new FileReader(fileName);
	    BufferedReader bufferedReader =  new BufferedReader(fileReader);
		this.url = bufferedReader.readLine();
		this.username = bufferedReader.readLine();
		this.password = bufferedReader.readLine();
		if (!TestSqlCon()) { System.out.println("HEY"); }
		bufferedReader.close();fileReader.close();
		if (this.debug) {
			System.out.println(this.url);
			System.out.println(this.username);
			System.out.println(this.password);
		}
	}
	
	public boolean CreateConnection (){
		try {
			LoadConData();
			} catch (IOException ex) {		
				met.ShowException(ex);
				return false;
			}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.con = DriverManager.getConnection (this.url,this.username,this.password);
			st = this.con.createStatement();
			}catch(Exception ex){
				met.ShowException(ex);
				return false;
			}
		return true;
	}
	
	public void DescargarPersonas() throws SQLException, IOException {
		CreateConnection();
		
		String sql = "Select * from "+dbPersonas;
		ResultSet rs = preguntaSql(sql);
		System.out.println("Query exitoso");
		String mail,nombre,apellido,nv,sexo;
		int rut;
		PrintWriter writer = new PrintWriter("c://POO//Persona.txt");
		while (rs.next()) 
		{
			nombre = rs.getString("Nombre");
			apellido = rs.getString("Apellido");
			rut = rs.getInt("Rut");
			nv = rs.getString("NumVer");
			sexo = rs.getString("Sexo");
			mail = rs.getString("Mail");
			writer.println(rut+"-"+nv+","+nombre+","+apellido+","+sexo+","+mail);
			
		}
		CloseConnection();	writer.close();
}
	
	public boolean TestSqlCon() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			if (this.url == null | this.username == null | this.password == null) { LoadConData();System.out.println("Data Con perdida"); }
			this.con = DriverManager.getConnection (this.url,this.username,this.password);
			st = this.con.createStatement();
			System.out.println("Coneccion sql exitosa"+con.getMetaData().getDatabaseProductName());
			this.con.close();
			return true;
		}catch (Exception ex) {
			met.ShowException(ex);
			return false;
		}

	}
	public void PedirListadoPersonas() throws SQLException, IOException {
			
			CreateConnection();
			String sql = "Select * from "+dbPersonas;
			ResultSet rs = preguntaSql(sql);
			System.out.println("Query exitoso");
			String sexo,nombre,apellido,nv,mail;
			int rut;
			
			PrintWriter writer = new PrintWriter("c://POO//Persona.txt");
			while (rs.next()) {
				nombre = rs.getString("Nombre");
				apellido = rs.getString("Apellido");
				rut = rs.getInt("Rut");
				nv = rs.getString("NumVer");
				sexo = rs.getString("Sexo");
				mail = rs.getString("Mail");
				writer.println(rut+"-"+nv+","+nombre+","+apellido+","+sexo+","+mail);
				System.out.println(nombre+" "+apellido+" Rut : "+rut+"-"+nv+" Sexo : "+sexo);

				
			}
			writer.close();
			CloseConnection();
		
	}
	
	public void DescargarData(String path) throws IOException, SQLException {
		CreateConnection();
		
		String sql = "Select * from "+dbPersonas;
		ResultSet rs = preguntaSql(sql);
		System.out.println("Query exitoso");
		String sexo,nombre,apellido,nv,mail;
		int rut;
		
		PrintWriter writer = new PrintWriter("c://POO//Persona.txt");
		while (rs.next()) {
			nombre = rs.getString("Nombre");
			apellido = rs.getString("Apellido");
			rut = rs.getInt("Rut");
			nv = rs.getString("NumVer");
			sexo = rs.getString("Sexo");
			mail = rs.getString("Mail");
			writer.println(rut+"-"+nv+","+nombre+","+apellido+","+sexo+","+mail);
		}
		writer.close();CloseConnection();
		
	}
	
	
	public void CloseConnection() throws SQLException {
		this.con.close();
	}
	
	public ResultSet preguntaSql (String s) throws SQLException {
			return st.executeQuery(s);
	}
	
	public int accionSql (String s) throws SQLException {
		return st.executeUpdate(s);		
	}
	
}
