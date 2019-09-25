package Controler;

import java.sql.*;
import Model.*;

public class ControlerRentalModel extends ControlerConection {
	// Variaveis comuns
	private String cnpj;
	private String description;
	private double price;
	private int totalRentalModel;
	
	// Variaveis do model
	ModelRentalModel[] modelRentalModel;
	
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

	public int getTotalRentalModel() {
		return totalRentalModel;
	}

	public void setTotalRentalModel(int totalRentalModel) {
		this.totalRentalModel = totalRentalModel;
	}

	public ModelRentalModel[] getModelRentalModel() {
		return modelRentalModel;
	}

	public void setModelRentalModel(ModelRentalModel[] modelRentalModel) {
		this.modelRentalModel = modelRentalModel;
	}
	
	// Selects
	public boolean selectRentalModel(String description) {		
		try {
			conecting();
			
			conn = conecting();
			comandSql = conn.createStatement();			
			rs = comandSql.executeQuery("select * from ModeloAluguel where descricao = '" + description + "'");
			
			if(rs.next()) {
				this.cnpj = rs.getString("cnpj");
				this.description = rs.getString("descricao");
				this.price = rs.getDouble("preco");
				
				closeConection(conn);
				
				return true;
				
			} else {
				closeConection(conn);
				return false;
				
			}
			
		} catch (SQLException e) {
			System.out.println("Erro no select: " + e);
			return false;
			
		}
	}
	
	public void selectAllRentalModel(String cnpj) {
		int i = 0;
		
		try {
			conecting();
			
			conn = conecting();
			comandSql = conn.createStatement();			
			rs = comandSql.executeQuery("select count(*) from ModeloAluguel where cnpj = '" + cnpj + "'");
			if(rs.next()) {
				totalRentalModel = Integer.parseInt(rs.getString("count(*)"));
			}
			modelRentalModel = new ModelRentalModel[totalRentalModel];
			
			rs = comandSql.executeQuery("select * from ModeloAluguel where cnpj = '" + cnpj + "'");
			while(rs.next()) {
				this.cnpj = rs.getString("cnpj");
				this.description = rs.getString("descricao");
				this.price = rs.getDouble("preco");
								
				modelRentalModel[i] = new ModelRentalModel(cnpj, description, price);
				i++;
			}
			
			closeConection(conn);
			
		} catch (SQLException e) {
			System.out.println("Erro no select: " + e);
			
		}
	}
	
	// Inserts
	public void insertRentalModel() {
		try {
			conecting();
			
			conn = conecting();
			comandSql = conn.createStatement();
			comandSql.executeUpdate("insert into ModeloAluguel values('" + cnpj + "', '" + description + "', " + price + ")");
			
			closeConection(conn);
			
		} catch (SQLException e) {
			System.out.println("Erro no insert: " + e);
		}
	}
	
	// Updates
	public void updateRentalModel(String descriptionOld) {
		try {
			conecting();
			
			conn = conecting();
			comandSql = conn.createStatement();
			
			comandSql.executeUpdate("update ModeloAluguel set descricao = '" + description + "', preco = " + price + " where cnpj = '" + cnpj + "' and descricao = '" + descriptionOld + "'");
			
			
			closeConection(conn);
			
		} catch (SQLException e) {
			System.out.println("Erro no update: " + e);
		}
	}
	
	// Deletes
	public void deleteSelectModel() {
		try {
			conecting();
			
			conn = conecting();
			comandSql = conn.createStatement();
			
			comandSql.executeUpdate("delete from ModeloAluguel where cnpj = '" + cnpj + "' and descricao = '" + description + "'");
			
			closeConection(conn);
			
		} catch (SQLException e) {
			System.out.println("Erro no delete: " + e);
		}
	}
}
