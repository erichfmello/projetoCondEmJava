package Controler;

import java.sql.*;

public class ControlerAppartmentOwner extends ControlerConection {
	private int appartment;
	private String cpf;
	
	// Construtores
	public ControlerAppartmentOwner() {	
	}

	public ControlerAppartmentOwner(int appartment, String cpf) {
		this.appartment = appartment;
		this.cpf = cpf;
	}
	
	// Inserts
	public void insertAppartmentOwner() {
		try {
			conecting();
			
			conn = conecting();
			comandSql = conn.createStatement();
			comandSql.executeUpdate("insert into apartamentopessoa values(" + appartment + ", '" + cpf + "')");
			System.out.println("Dados inseridos: " + conn);
			
			closeConection(conn);
			
		} catch (SQLException e) {
			System.out.println("Erro insert: " + e);
		}
	}
}
