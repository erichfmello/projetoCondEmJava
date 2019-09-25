package Controler;

import java.sql.*;

public class ControlerConection {
	Connection conn;
	Statement comandSql;
	ResultSet rs;
	
	public Connection conecting() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Drive localizado");
			
			String bdUrl = "jdbc:mysql://localhost:3306/virtualdoorman";
			String bdUser = "root";
			String bdPassword = "Pantru123";
			
			conn = DriverManager.getConnection(bdUrl, bdUser, bdPassword);
			System.out.println("Conectado: " + conn);
			
			return conn;
			
		} catch (ClassNotFoundException e) {
			System.out.println("Drive não localizado: " + e);
			return null;
		} catch (SQLException e) {
			System.out.println("Erro na conexao: " + e);
			return null;
		}
	}
	
	public void closeConection(Connection conn) {
		try {
			conn.close();
			System.out.println("Conexao fechada: " + conn);
			
		} catch(SQLException e) {
			System.out.println("Erro para fechar conexao: " + e);
			
		}
	}

}
