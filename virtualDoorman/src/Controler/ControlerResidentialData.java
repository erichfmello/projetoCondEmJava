package Controler;

import java.sql.*;

import Model.ModelResidentialData;

public class ControlerResidentialData {
	private String name, address, cep, neighborhood, uf, cnpj;
	private int number, appartmentLastFloor, floor, appartmentPerFloor, Block;

	private ModelResidentialData residentialData;
	
	// Variaveis da coneção
	Connection conn;
	Statement comandsSql;
	ResultSet rs;
	
	public ControlerResidentialData() {
		residentialData = new ModelResidentialData();
	}	
	
	public ControlerResidentialData(String name, String address, String cep, String neighborhood, String uf,
			String cnpj, int number, int appartmentLastFloor, int floor, int appartmentPerFloor, int block) {
		this.name = name;
		this.address = address;
		this.cep = cep;
		this.neighborhood = neighborhood;
		this.uf = uf;
		this.cnpj = cnpj;
		this.number = number;
		this.appartmentLastFloor = appartmentLastFloor;
		this.floor = floor;
		this.appartmentPerFloor = appartmentPerFloor;
		Block = block;
		residentialData = new ModelResidentialData(name, address, cep, neighborhood, uf, cnpj, number, appartmentLastFloor, floor, appartmentPerFloor, block);
	}

	// getters and setters
	public ModelResidentialData getResidentialData() {
		return residentialData;
	}

	public void setResidentialData(ModelResidentialData residentialData) {
		this.residentialData = residentialData;
	}

	// Comandos do SQL
	public Connection conectingResidentialData() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Drive localizado");
			
			String bdUrl = "jdbc:mysql://localhost:3306/virtualdoorman";
			String bdUser = "root";
			String bdPassword = "Pantru123";
			
			conn = DriverManager.getConnection(bdUrl, bdUser, bdPassword);
			System.out.println("Conexão realizada com sucesso: " + conn);
			
			return conn;
			
		} catch (ClassNotFoundException e) {
			System.out.println("Drive JDBC não localizado: " + e);
			return null;
			
		} catch (SQLException e) {
			System.out.println("Problemas na conexão: " + e);
			return null;
		}
	}
	
	public void closeResidentialData(Connection conn) {
		try {
			conn.close();
			System.out.println("Conexão fechada: " + conn);
			
		} catch (SQLException e) {
			System.out.println("Erro para fechar a conexão: " + e);
		}
	}
	
	public void insertResidentialData() {
		try {			
			conectingResidentialData();
			conn = conectingResidentialData();
			comandsSql = conn.createStatement();
			
			comandsSql.executeUpdate("insert into condominio values (" + cnpj + ", '" + name + "', " + appartmentLastFloor + ", " + floor
					+ ", " + appartmentPerFloor + ", " + Block + ");");

			comandsSql.executeUpdate("insert into condominioendereco(endereco, cep, bairro, uf, numero, cnpj) values('" + address + "', '" + cep
					+ "', '" + neighborhood + "', '" + uf + "', '" + String.valueOf(number) + "', '" + cnpj + "')");
			
			System.out.println("Dados inseridos");
			closeResidentialData(conn);
			
		} catch (SQLException e) {
			System.out.print("Erro no insert: " + e);
		}
		
	}
	
	public void selectResidentialData() {
		try {
			conectingResidentialData();
			conn = conectingResidentialData();
			comandsSql = conn.createStatement();
			rs = comandsSql.executeQuery("select * from condominioendereco ce inner join condominio c on ce.cnpj = c.cnpj where c.cnpj = " + 20);
			
			while(rs.next()) {
				this.name = rs.getString("c.nome");
				this.cnpj = rs.getString("c.cnpj");
				this.address = rs.getString("ce.endereco");
				this.cep = rs.getString("ce.cep");
				this.neighborhood = rs.getString("ce.bairro");
				this.uf = rs.getString("ce.uf");
				
				this.appartmentLastFloor = Integer.parseInt(rs.getString("c.apartamentosNaCobertura"));
				this.floor = Integer.parseInt(rs.getString("c.andares"));
				this.appartmentPerFloor = Integer.parseInt(rs.getString("c.apartamentosPorAndar"));
				this.Block = Integer.parseInt(rs.getString("c.numerosDeBlocos"));
				this.number = Integer.parseInt(rs.getString("ce.numero"));
				
				residentialData = new ModelResidentialData(name, address, cep, neighborhood, uf, cnpj, number, appartmentLastFloor, 
						floor, appartmentPerFloor, Block);
			}
			
			closeResidentialData(conn);
			
		} catch (SQLException e) {
			System.out.println("Erro de seleção: " + e);
		}
	}
}
