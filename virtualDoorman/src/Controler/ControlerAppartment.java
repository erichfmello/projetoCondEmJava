package Controler;

import java.sql.*;

import Model.ModelAppartamentThisFloor;

public class ControlerAppartment extends ControlerConection {
	private int appartment;
	private int floor;
	private int finalNumber;
	private String block;
	private String cnpj;
	
	private int totalAppartmentThisFloor;
	private int totalAppartaments;
	
	// Variaveis de gera��o dos model
	public ModelAppartamentThisFloor[] modelAppartamentThisFloors;
	
	// Contrutor
	public ControlerAppartment() {
	}
		
	public ControlerAppartment(int appartment, int floor, int finalNumber, String block) {
		super();
		this.appartment = appartment;
		this.floor = floor;
		this.finalNumber = finalNumber;
		this.block = block;
	}
	
	// Getter and setter
	public int getAppartment() {
		return appartment;
	}

	public void setAppartment(int appartment) {
		this.appartment = appartment;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getFinalNumber() {
		return finalNumber;
	}

	public void setFinalNumber(int finalNumber) {
		this.finalNumber = finalNumber;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public int getTotalAppartmentThisFloor() {
		return totalAppartmentThisFloor;
	}

	public void setTotalAppartmentThisFloor(int totalAppartmentThisFloor) {
		this.totalAppartmentThisFloor = totalAppartmentThisFloor;
	}

	public int getTotalAppartaments() {
		return totalAppartaments;
	}

	public void setTotalAppartaments(int totalAppartaments) {
		this.totalAppartaments = totalAppartaments;
	}

	public ModelAppartamentThisFloor[] getModelAppartamentThisFloors() {
		return modelAppartamentThisFloors;
	}

	public void setModelAppartamentThisFloors(ModelAppartamentThisFloor[] modelAppartamentThisFloors) {
		this.modelAppartamentThisFloors = modelAppartamentThisFloors;
	}

	public ControlerAppartment(ModelAppartamentThisFloor[] modelAppartamentThisFloors) {
		super();
		this.modelAppartamentThisFloors = modelAppartamentThisFloors;
	}
	
	// Inserts
	public void insertAppartment(String cnpj) {
		try {
			conecting();
			
			conn = conecting();
			comandSql = conn.createStatement();
			comandSql.executeUpdate("insert into apartamentos values(" + appartment + ", " + floor + ", " + finalNumber + ", " + block + ", " + cnpj + ")");
			System.out.println("Dados inseridos com sucesso: " + conn);
			
			closeConection(conn);
			
		} catch(SQLException e) {
			System.out.println("Erro na inser��o: " + e);
		}
	}
	
	// Selects
	public void selectAppartment(int floor) {
		int i = 0;
		try {
			conecting();
			
			conn = conecting();
			comandSql = conn.createStatement();
			rs = comandSql.executeQuery("select count(*) from apartamentos where cnpj = '20' and apartamento between " + floor*10 + " and " + floor*20);
			
			if(rs.next()) {
				totalAppartmentThisFloor = rs.getInt("count(*)");
			}
			
			modelAppartamentThisFloors = new ModelAppartamentThisFloor[totalAppartmentThisFloor];
			rs = comandSql.executeQuery("select * from apartamentos where cnpj = '20' and apartamento between " + floor*10 + " and " + floor*20);
			
			while(rs.next()) {
				appartment = rs.getInt("apartamento");
				this.floor = rs.getInt("andar");
				finalNumber = rs.getInt("final");
				block = rs.getString("bloco");
				cnpj = rs.getString("cnpj");
				
				modelAppartamentThisFloors[i] = new ModelAppartamentThisFloor(appartment, this.floor, finalNumber, block, cnpj);
				i++;
			}
			System.out.println("Select executado com sucesso: " + conn);
			
			closeConection(conn);
			
			
		} catch (SQLException e) {
			System.out.println("Erro do select: " + e);
		}
	}
	
	public void selectFloor(int appartment) {
		try {
			conecting();
			
			conn = conecting();
			comandSql = conn.createStatement();
			rs = comandSql.executeQuery("select * from apartamentos where cnpj = '20' and apartamento = " + appartment);
			
			while(rs.next()) {
				this.floor = rs.getInt("andar");
			}
			
			closeConection(conn);
			
			System.out.println("Select executado com sucesso: " + conn);
			
		} catch (SQLException e) {
			System.out.println("Select error: " + e);
		}
	}
	
	public void selectAllAppartaments(String cnpj) {
		int i = 0;
		
		try {
			conecting();
			
			conn = conecting();
			comandSql = conn.createStatement();
			rs = comandSql.executeQuery("select count(*) from apartamentos where cnpj = '" + cnpj + "'");
			
			if(rs.next()) {
				totalAppartaments = rs.getInt("count(*)");
			}
			
			modelAppartamentThisFloors = new ModelAppartamentThisFloor[totalAppartaments];
			
			rs = comandSql.executeQuery("select * from apartamentos where cnpj = '" + cnpj + "'");
			
			while(rs.next()) {
				appartment = rs.getInt("apartamento");
				floor = rs.getInt("andar");
				finalNumber = rs.getInt("final");
				block = rs.getString("bloco");
				cnpj = rs.getString("cnpj");
				
				modelAppartamentThisFloors[i] = new ModelAppartamentThisFloor(appartment, floor, finalNumber, block, cnpj);
				i++;
			}
			
			closeConection(conn);
			
		} catch (SQLException e) {
			System.out.println("Erro no select: " + e);
			
		}
	}
}
