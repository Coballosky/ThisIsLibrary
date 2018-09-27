package ConnectionHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import Comun.Metodos;

/**
 * @author Nicolas
 *	
 *
 **/


public class SqlConection {
	private boolean debug = true;
	
	Metodos met = new Metodos();
	private String url,username,password;	
	private Connection con;
	public Statement st;
	private String dbPersonas = "Persona";

	
	//
	
	String dbName = "POO";
	
	// -------------------------------------------------------------- //
	
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
			if (this.url == null | this.username == null | this.password == null) { LoadConData(); }
			this.con = DriverManager.getConnection (this.url,this.username,this.password);
			st = this.con.createStatement();
			this.con.close();
			return true;
		}catch (Exception ex) {
			met.ShowException(ex);
			return false;
		}

	}

	public void DescargaData() {
		try {
			DescargarPersonas();

			
				/*
				 * 
				 * TODO: AGREGAR LAS DESCARGAS DE TODA LA DATA QUE OCUPARA EL PROGRAMA 
				 * 
				 * 
				 */
			
			
			} catch (SQLException | IOException ex) {
				met.ShowException(ex);
			}
		
	}
	
	
	public int CheckDB() {
		CreateConnection();
		String db;
		boolean dbFound = false;
		
		ResultSet dbs = preguntaSql("show databases");	
		try {
			while (dbs.next()) {
				db = dbs.getString("Database");
				if (db == dbName) { dbFound = true; }
			}
		} catch (SQLException ex) {
			met.ShowException(ex);
		}

		if (dbFound) {
			if (debug) { System.out.println("DB encontrada"); } 
			return 1;
		}else {
			/*
			 * Crea la la db en el sv sql
			 */
			if (debug) { System.out.println("DB no encontrada, Creando"); } 
			
			accionSql("create database "+dbName+";");
			
			accionSql("use "+dbName+";");
			
			accionSql("CREATE TABLE `Persona` (\r\n" + 
					"	`ID` INT(3) UNSIGNED NOT NULL AUTO_INCREMENT,\r\n" + 
					"	`Rut` INT(8) UNSIGNED NOT NULL,\r\n" + 
					"	`NumVer` TINYTEXT NOT NULL,\r\n" + 
					"	`Nombre` CHAR(50) NOT NULL,\r\n" + 
					"	`Apellido` CHAR(50) NOT NULL,\r\n" + 
					"	`Mail` CHAR(50) NULL DEFAULT NULL,\r\n" + 
					"	`Sexo` CHAR(50) NULL DEFAULT NULL,\r\n" + 
					"	`Direccion` CHAR(50) NULL DEFAULT NULL,\r\n" + 
					"	PRIMARY KEY (`ID`),\r\n" + 
					"	UNIQUE INDEX `Rut` (`Rut`),\r\n" + 
					"	FULLTEXT INDEX `Nombre` (`Nombre`),\r\n" + 
					"	FULLTEXT INDEX `Apellido` (`Apellido`),\r\n" + 
					"	FULLTEXT INDEX `Sexo` (`Sexo`)\r\n" + 
					")\r\n" + 
					"COMMENT='Contiene la informacion de las personas'\r\n" + 
					"COLLATE='utf8mb4_0900_ai_ci'\r\n" + 
					"ENGINE=InnoDB\r\n" + 
					"AUTO_INCREMENT=34;");
			
			accionSql("CREATE TABLE `Libros` (\r\n" + 
					"	`ID` SMALLINT(5) UNSIGNED NOT NULL AUTO_INCREMENT,\r\n" + 
					"	`IDTexto` TINYTEXT NOT NULL,\r\n" + 
					"	`Numero` INT(11) NULL DEFAULT NULL,\r\n" + 
					"	`Titulo` TINYTEXT NULL,\r\n" + 
					"	`Autor` TINYTEXT NULL,\r\n" + 
					"	`Idioma` TINYTEXT NULL,\r\n" + 
					"	`Estado` TINYTEXT NULL,\r\n" + 
					"	`Rentado` TINYINT(3) UNSIGNED ZEROFILL NULL DEFAULT NULL,\r\n" + 
					"	`Tema` TINYTEXT NULL,\r\n" + 
					"	PRIMARY KEY (`ID`)\r\n" + 
					")\r\n" + 
					"COLLATE='utf8mb4_0900_ai_ci'\r\n" + 
					"ENGINE=InnoDB"
					+ ";");
			
			accionSql("CREATE TABLE `Usuarios` (\r\n" + 
					"	`ID` INT(11) NOT NULL AUTO_INCREMENT,\r\n" + 
					"	`User` CHAR(16) NOT NULL,\r\n" + 
					"	`Password` CHAR(16) NOT NULL,\r\n" + 
					"	`Rut` CHAR(8) NOT NULL,\r\n" + 
					"	PRIMARY KEY (`ID`),\r\n" + 
					"	UNIQUE INDEX `User` (`User`),\r\n" + 
					"	FULLTEXT INDEX `Rut` (`Rut`)\r\n" + 
					")\r\n" + 
					"COLLATE='utf8mb4_0900_ai_ci'\r\n" + 
					"ENGINE=InnoDB\r\n" + 
					";");
			CloseConnection();
			return 2;
		}
		
		
		
	}
	
	
	public void CloseConnection() {
		try {
		this.con.close();
		}catch (Exception ex) {
			met.ShowException(ex);
		}
	}
	public ResultSet preguntaSql (String s) {
		try {
		return st.executeQuery(s);
		}catch (Exception ex) {
			met.ShowException(ex);
			return null;
		}
	}
	public int accionSql (String s){
		try {
			return st.executeUpdate(s);
		} catch (SQLException ex) {
			met.ShowException(ex);
			return 0;
		}		
	}
}

