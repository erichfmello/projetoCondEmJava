package Controler;

import java.sql.*;
import Model.*;

public class ControlerCommunArea {
	private String name;
	private String cnpj;
	private int totalAreas;
	
	// Variaveis do model
	ModelCommunArea[] modelCommunArea;
	
	// Variaveis de conexao
	Connection conn;
	Statement comandSql;
	ResultSet rs;
	
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
	
	// Conexao
	public Connection conectCommunArea() {
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
			System.out.println("Drive n�o localizado: " + e);
			return null;
			
		} catch(SQLException e) {
			System.out.println("Problema na conex�o: " + e);
			return null;
		}
	}
	
	public void closeConectionCommunArea(Connection conn) {
		try {
			conn.close();
			System.out.println("Conexao fechada: " + conn);
			
		} catch (SQLException e) {
			System.out.println("Erro para fechar conexao: " + e);
		}
	}
	
	// Selects
	public void selectCommunArea(String cnpj) {
		int index = 0;
						
		try {
			conectCommunArea();
			
			conn = conectCommunArea();
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
			
			closeConectionCommunArea(conn);
			
		} catch (SQLException e) {
			System.out.println("Erro de select: " + e);
		}
	}
	
	// Inserts
	public void insertCommunArea() {
		try {
			conectCommunArea();
			
			conn = conectCommunArea();
			comandSql = conn.createStatement();
			comandSql.executeUpdate("insert into areascomuns(nome, cnpj) values('" + name + "', '" + this.cnpj + "')");
			System.out.println("Dados inseridos com sucesso: " + conn);
			
			closeConectionCommunArea(conn);
			
		} catch (SQLException e) {
			System.out.println("Erro no insert: " + e);
			
		}
	}
	
	// Update
	public void updateCommunArea(String nameNew, String nameOld, String cnpj) {
		try {
			conectCommunArea();
			
			conn = conectCommunArea();
			comandSql = conn.createStatement();
			comandSql.executeUpdate("update areascomuns set nome = '" + nameNew + "' where cnpj = '" + cnpj + "' and nome = '" + nameOld + "'");
			
			System.out.println("Dados inseridos com sucesso: " + conn);
			
			closeConectionCommunArea(conn);
			
		} catch (SQLException e) {
			System.out.println("Erro no update: " + e);
			
		}
	}
	
	// Delete
	public void deleteCommunArea(String name) {
		try {
			conectCommunArea();
			
			conn = conectCommunArea();
			comandSql = conn.createStatement();
			comandSql.executeUpdate("delete from areascomuns where nome = '" + name + "'");
			System.out.println("Delete com sucesso: " + conn);
			
			closeConectionCommunArea(conn);
			
		} catch (SQLException e) {
			System.out.println("Erro no delete: " + e);
			
		}
	}
}
