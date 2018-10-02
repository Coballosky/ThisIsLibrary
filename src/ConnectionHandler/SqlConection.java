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
	private boolean debug = false;
	
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
			if (debug) { System.out.println("DB no encontrada, Creando"); } 
			
			/**
			  " -- MySQL Workbench Synchronization\r\n" + 
					"-- Generated: 2018-09-29 17:38\r\n" + 
					"-- Model: New Model\r\n" + 
					"-- Version: 1.3\r\n" + 
					"-- Project: Name of the project\r\n" + 
					"-- Author: Nicolas\r\n" +  
			 */
		try {	
			accionSql("SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;");
			accionSql("SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;");
			accionSql("SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';)";

			accionSql("CREATE SCHEMA IF NOT EXISTS `" + dbName + "` DEFAULT CHARACTER SET utf8 ;"); 

			accionSql("CREATE TABLE IF NOT EXISTS `" + dbName + "`.`Libro` (\r\n" + 
					"  `idLibro` INT(11) NOT NULL,\r\n" + 
					"  `idAlfaNumerico` VARCHAR(9) NOT NULL,\r\n" + 
					"  `nombre` VARCHAR(45) NOT NULL,\r\n" + 
					"  `autor` VARCHAR(45) NOT NULL,\r\n" + 
					"  `tema` VARCHAR(45) NOT NULL,\r\n" + 
					"  `tiempoPermitido` INT(11) NOT NULL,\r\n" + 
					"  PRIMARY KEY (`idLibro`),\r\n" + 
					"  UNIQUE INDEX `idAlfaNumerico_UNIQUE` (`idAlfaNumerico` ASC) VISIBLE)\r\n" + 
					"ENGINE = InnoDB\r\n" + 
					"DEFAULT CHARACTER SET = utf8;" );
			
			accionSql("CREATE TABLE IF NOT EXISTS `" + dbName + "`.`Persona` (\r\n" + 
					"  `rut` INT(10) UNSIGNED NOT NULL,\r\n" + 
					"  `nombre` VARCHAR(45) NOT NULL,\r\n" + 
					"  `apellido` VARCHAR(45) NOT NULL,\r\n" + 
					"  `mail` VARCHAR(45) NOT NULL,\r\n" + 
					"  `cargo` VARCHAR(45) NOT NULL,\r\n" + 
					"  PRIMARY KEY (`rut`),\r\n" + 
					"  UNIQUE INDEX `rut_UNIQUE` (`rut` ASC) VISIBLE)\r\n" + 
					"ENGINE = InnoDB\r\n" + 
					"DEFAULT CHARACTER SET = utf8;");
				
			accionSql("CREATE TABLE IF NOT EXISTS `" + dbName + "`.`Renta` (\r\n" + 
					"  `idRenta` INT(11) NOT NULL AUTO_INCREMENT,\r\n" + 
					"  `rutOperador` INT(10) UNSIGNED NOT NULL,\r\n" + 
					"  `rutCliente` INT(11) NOT NULL,\r\n" + 
					"  `ObjetoRenta` VARCHAR(9) NOT NULL,\r\n" + 
					"  PRIMARY KEY (`idRenta`),\r\n" + 
					"  UNIQUE INDEX `idRenta_UNIQUE` (`idRenta` ASC) VISIBLE,\r\n" + 
					"  INDEX `fk_Renta_Persona_idx` (`rutOperador` ASC) VISIBLE,\r\n" + 
					"  INDEX `fk_Renta_Libro1_idx` (`ObjetoRenta` ASC) VISIBLE,\r\n" + 
					"  CONSTRAINT `fk_Renta_Persona`\r\n" + 
					"    FOREIGN KEY (`rutOperador`)\r\n" + 
					"    REFERENCES `" + dbName + "`.`Persona` (`rut`)\r\n" + 
					"    ON DELETE NO ACTION\r\n" + 
					"    ON UPDATE NO ACTION,\r\n" + 
					"  CONSTRAINT `fk_Renta_Libro1`\r\n" + 
					"    FOREIGN KEY (`ObjetoRenta`)\r\n" + 
					"    REFERENCES `" + dbName + "`.`Libro` (`idAlfaNumerico`)\r\n" + 
					"    ON DELETE NO ACTION\r\n" + 
					"    ON UPDATE NO ACTION,\r\n" + 
					"  CONSTRAINT `fk_Renta_Insumo1`\r\n" + 
					"    FOREIGN KEY (`ObjetoRenta`)\r\n" + 
					"    REFERENCES `" + dbName + "`.`Insumo` (`idAlfaNumerico`)\r\n" + 
					"    ON DELETE NO ACTION\r\n" + 
					"    ON UPDATE NO ACTION)\r\n" + 
					"ENGINE = InnoDB\r\n" + 
					"DEFAULT CHARACTER SET = utf8;");
					
			accionSql("CREATE TABLE IF NOT EXISTS `" + dbName + "`.`Insumo` (\r\n" + 
					"  `idInsumo` INT(11) NOT NULL,\r\n" + 
					"  `idAlfaNumerico` VARCHAR(9) NOT NULL,\r\n" + 
					"  `nombre` VARCHAR(45) NOT NULL,\r\n" + 
					"  `modelo` VARCHAR(45) NOT NULL,\r\n" + 
					"  `tiempoPermitido` INT(11) NOT NULL,\r\n" + 
					"  PRIMARY KEY (`idInsumo`),\r\n" + 
					"  UNIQUE INDEX `idAlfaNumerico_UNIQUE` (`idAlfaNumerico` ASC) VISIBLE)\r\n" + 
					"ENGINE = InnoDB\r\n" + 
					"DEFAULT CHARACTER SET = utf8;");
					
			accionSql("CREATE TABLE IF NOT EXISTS `"+ dbName +"`.`Estudiante` (\r\n" + 
					"  `rut` INT(10) UNSIGNED NOT NULL,\r\n" + 
					"  `nombre` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
					"  `apellido` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
					"  `mail` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
					"  PRIMARY KEY (`rut`),\r\n" + 
					"  UNIQUE INDEX `rut_UNIQUE` (`rut` ASC) VISIBLE)\r\n" + 
					"ENGINE = InnoDB\r\n" + 
					"DEFAULT CHARACTER SET = utf8;");
			
			accionSql("CREATE TABLE IF NOT EXISTS `" + dbName + "`.`DataLogin` (\r\n" + 
					"  `Persona_rut` INT(10) UNSIGNED NOT NULL,\r\n" + 
					"  `Password` VARCHAR(45) NOT NULL,\r\n" + 
					"  INDEX `fk_DataLogin_Persona1_idx` (`Persona_rut` ASC) VISIBLE,\r\n" + 
					"  CONSTRAINT `fk_DataLogin_Persona1`\r\n" + 
					"    FOREIGN KEY (`Persona_rut`)\r\n" + 
					"    REFERENCES `" + dbName + "`.`Persona` (`rut`)\r\n" + 
					"    ON DELETE NO ACTION\r\n" + 
					"    ON UPDATE NO ACTION)\r\n" + 
					"ENGINE = InnoDB\r\n" + 
					"DEFAULT CHARACTER SET = utf8;\r\n");
			
			accionSql("SET SQL_MODE=@OLD_SQL_MODE;);
			accionSql("SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;");
			accionSql("SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;");
			
			return 2;
		}catch (Exception ex) {
			
		}
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

