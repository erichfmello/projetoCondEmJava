package Controler;

import java.sql.*;
import Model.*;

public class ControlerCommunArea extends ControlerConection {
	private String name;
	private String cnpj;
	private int totalAreas;
	
	// Variaveis do model
	ModelCommunArea[] modelCommunArea;
	
	// Contrutores
	public ControlerCommunArea() {
	}

	public ControlerCommunArea(String name, String cnpj) {
		super();
		this.name = name;
		this.cnpj = cnpj;
	}

	// getter and setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public int getTotalAreas() {
		return totalAreas;
	}

	public void setTotalAreas(int totalAreas) {
		this.totalAreas = totalAreas;
	}

	public ModelCommunArea[] getModelCommunArea() {
		return modelCommunArea;
	}

	public void setModelCommunArea(ModelCommunArea[] modelCommunArea) {
		this.modelCommunArea = modelCommunArea;
	}
	
	// Selects
	public void selectCommunArea(String cnpj) {
		int index = 0;
						
		try {
			conecting();
			
			conn = conecting();
			comandSql = conn.createStatement();
			
			rs = comandSql.executeQuery("select count(*) from areascomuns where cnpj = " + cnpj);
			
			if(rs.next()) {
				totalAreas = rs.getInt("count(*)");				
			}
			
			System.out.println(totalAreas);
			modelCommunArea = new ModelCommunArea[totalAreas];
			
			rs = comandSql.executeQuery("select * from areascomuns where cnpj = " + cnpj + " order by nome");
			
			while(rs.next()) {
				name = rs.getString("nome");
				this.cnpj = rs.getString("cnpj");
				
				modelCommunArea[index] = new ModelCommunArea(name, cnpj);
				index++;
			}
			
			System.out.println("Select executado com sucesso: " + conn);
			
			closeConection(conn);
			
		} catch (SQLException e) {
			System.out.println("Erro de select: " + e);
		}
	}
	
	// Inserts
	public void insertCommunArea() {
		try {
			conecting();
			
			conn = conecting();
			comandSql = conn.createStatement();
			comandSql.executeUpdate("insert into areascomuns(nome, cnpj) values('" + name + "', '" + this.cnpj + "')");
			System.out.println("Dados inseridos com sucesso: " + conn);
			
			closeConection(conn);
			
		} catch (SQLException e) {
			System.out.println("Erro no insert: " + e);
			
		}
	}
	
	// Update
	public void updateCommunArea(String nameNew, String nameOld, String cnpj) {
		try {
			conecting();
			
			conn = conecting();
			comandSql = conn.createStatement();
			comandSql.executeUpdate("update areascomuns set nome = '" + nameNew + "' where cnpj = '" + cnpj + "' and nome = '" + nameOld + "'");
			
			System.out.println("Dados inseridos com sucesso: " + conn);
			
			closeConection(conn);
			
		} catch (SQLException e) {
			System.out.println("Erro no update: " + e);
			
		}
	}
	
	// Delete
	public void deleteCommunArea(String name) {
		try {
			conecting();
			
			conn = conecting();
			comandSql = conn.createStatement();
			comandSql.executeUpdate("delete from areascomuns where nome = '" + name + "'");
			System.out.println("Delete com sucesso: " + conn);
			
			closeConection(conn);
			
		} catch (SQLException e) {
			System.out.println("Erro no delete: " + e);
			
		}
	}
}
