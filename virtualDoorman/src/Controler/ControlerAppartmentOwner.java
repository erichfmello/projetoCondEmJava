package Controler;

import java.sql.*;

public class ControlerAppartmentOwner {
	private int appartment;
	private String cpf;
	
	// Variaveis de conexao
	Connection conn;
	Statement comandoSql;
	ResultSet rs;
	
	// Construtores
	public ControlerAppartmentOwner() {	
	}

	public ControlerAppartmentOwner(int appartment, String cpf) {
		this.appartment = appartment;
		this.cpf = cpf;
	}
	
	public Connection conectarAppartmentOwner() {
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
			System.out.println("Problema na conexão: " + e);
			return null;
		}
	}
	
	public void closeConectionAppartmentOwner(Connection conn) {
		try {
			conn.close();
			System.out.println("Conexao fechada: " + conn);
			
		} catch(SQLException e) {
			System.out.println("Erro para fechar conexao: " + e);
			
		}
	}
	
	public void insertAppartmentOwner() {
		try {
			conectarAppartmentOwner();
			conn = conectarAppartmentOwner();
			comandoSql = conn.createStatement();
			comandoSql.executeUpdate("insert into apartamentopessoa values(" + appartment + ", '" + cpf + "')");
			System.out.println("Dados inseridos: " + conn);
			
			closeConectionAppartmentOwner(conn);
			
		} catch (SQLException e) {
			System.out.println("Erro insert: " + e);
		}
	}
}
