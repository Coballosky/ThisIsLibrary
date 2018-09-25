package ConnectionHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

/**
 * @author Nicolas
 *	Esta clase fue pensada con un administrador de conecciones amigable en cuanto a los nombres
 *	un lugar unico en caso de requerir cambiar la direccion del sql clave y usuario
 *
 **/


public class SqlConection {
	private String url;
	private String username;
	private String password;
	public Connection con;
	public Statement st;
	private String dbPersonas = "Persona";
	
	public void LoadConData() throws IOException {
		String fileName = "c://POO//SqlData.txt";
		FileReader fileReader =   new FileReader(fileName);
	    BufferedReader bufferedReader =  new BufferedReader(fileReader);
		this.url = bufferedReader.readLine();
		this.username = bufferedReader.readLine();
		this.password = bufferedReader.readLine();
		bufferedReader.close();
	}
	
	public void CreateConnection () throws IOException {
		
		try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection (this.url,this.username,this.password);
		st = con.createStatement();
		}catch(Exception Ex){
			System.out.println(Ex);
		}
	}
	
	public void CloseConnection() throws SQLException {
		con.close();
	}
	
	public ResultSet preguntaSql (String s) throws SQLException {
			return st.executeQuery(s);
	}
	
	public int accionSql (String s) throws SQLException {
	
		return st.executeUpdate(s);		
	}
	
	public boolean TestSqlCon() throws IOException {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection (this.url,this.username,this.password);
			st = con.createStatement();			
			con.close();
			System.out.println("Coneccion sql exitosa");
			return true;
		}catch (Exception Ex) {
			return false;
		}

	}
	public void PedirListadoPersonas() throws SQLException, IOException {
			
			CreateConnection();
			String sql = "Select * from "+dbPersonas;
			ResultSet rs = preguntaSql(sql);
			System.out.println("Query exitoso");
			
			while (rs.next()) {
				
				int ID = rs.getInt("ID");
				String Name = rs.getString("Nombre");
				String Apellido = rs.getString("Apellido");
				int Rut = rs.getInt("Rut");
				String NV = rs.getString("NumVer");
				String Sex = rs.getString("Sexo");
				
				System.out.println(ID+" "+Name+" "+Apellido+" Rut : "+Rut+"-"+NV+" Sexo : "+Sex);
				
			}

		
	}
	
}
