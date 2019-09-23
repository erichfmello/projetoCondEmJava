package Controler;

import java.sql.*;

import javax.swing.JOptionPane;

import Model.*;

public class ControlerRentalModel {
	// Variaveis comuns
	private String cnpj;
	private String description;
	private double price;
	
	// Variaveis do model
	ModelRentalModel modelRentalModel;
	
	// Variaveis de conexao
	Connection conn;
	Statement comandSql;
	ResultSet rs;
	
	// Construtores
	public ControlerRentalModel(){
	}

	public ControlerRentalModel(String cnpj, String description, double price) {
		this.cnpj = cnpj;
		this.description = description;
		this.price = price;
	}

	// getter and setter
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public ModelRentalModel getModelRentalModel() {
		return modelRentalModel;
	}

	public void setModelRentalModel(ModelRentalModel modelRentalModel) {
		this.modelRentalModel = modelRentalModel;
	}

	// Conexao
	private Connection conectRentalModel() {
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
			System.out.println("Classe n�o localizada");
			return null;
			
		} catch (SQLException e) {
			System.out.println("Problema na conex�o: " + e);
			return null;
			
		}
	}
	
	private void closeConectionRentalModel(Connection conn) {
		try {
			conn.close();
			System.out.println("Conexao fechada: " + conn);
			
		} catch (SQLException e) {
			System.out.println("Erro para fechar conexao: " + e);
			
		}
	}
	
	// Selects
	public boolean selectRentalModel(String cnpj) {
		try {
			conectRentalModel();
			
			conn = conectRentalModel();
			comandSql = conn.createStatement();
			rs = comandSql.executeQuery("select * from ModeloAluguel where cnpj = '" + cnpj + "'");
			
			if(rs.next()) {
				this.cnpj = rs.getString("cnpj");
				this.description = rs.getString("descricao");
				this.price = rs.getDouble("preco");
								
				modelRentalModel = new ModelRentalModel(cnpj, description, price);
				
				closeConectionRentalModel(conn);
				
				return true;
				
			} else {
				closeConectionRentalModel(conn);
				return false;
				
			}
			
		} catch (SQLException e) {
			System.out.println("Erro no select: " + e);
			return false;
			
		}
	}
	
	// Inserts
	public void insertRentalModel() {
		try {
			conectRentalModel();
			
			conn = conectRentalModel();
			comandSql = conn.createStatement();
			comandSql.executeUpdate("insert into ModeloAluguel values('" + cnpj + "', '" + description + "', " + price + ")");
			
			modelRentalModel = new ModelRentalModel(cnpj, description, price);
			
			closeConectionRentalModel(conn);
			
		} catch (SQLException e) {
			System.out.println("Erro no insert: " + e);
		}
	}
	
	// Updates
	public void updateRentalModel() {
		try {
			conectRentalModel();
			
			conn = conectRentalModel();
			comandSql = conn.createStatement();
			
			comandSql.executeUpdate("update ModeloAluguel set descricao = '" + description + "' where cnpj = '" + cnpj + "'");
			comandSql.executeUpdate("update ModeloAluguel set preco = '" + price + "' where cnpj = '" + cnpj + "'");
			
			closeConectionRentalModel(conn);
			
		} catch (SQLException e) {
			System.out.println("Erro no update: " + e);
		}
	}
}
