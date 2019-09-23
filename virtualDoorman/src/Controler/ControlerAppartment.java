package Controler;

import java.sql.*;

import Model.ModelAppartamentThisFloor;

public class ControlerAppartment {
	private int appartment;
	private int floor;
	private int finalNumber;
	private String block;
	private String cnpj;
	
	private int totalAppartmentThisFloor;
	
	// Variaveis de gera��o dos model
	public ModelAppartamentThisFloor[] modelAppartamentThisFloors;
	
	// Variaveis de conexao
	Connection conn;
	Statement comandoSql;
	ResultSet rs;
	
	// Contrutor
	public ControlerAppartment() {
	}
		
	public ControlerAppartment(int appartment, int floor, int finalNumber, String block) {
		this.appartment = appartment;
		this.floor = floor;
		this.finalNumber = finalNumber;
		this.block = block;
	}
	
	public Connection connectingAppartment() {
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
		} catch (SQLException e) {
			System.out.println("Erro na conexao: " + e);
			return null;
		}
	}
	
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

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Statement getComandoSql() {
		return comandoSql;
	}

	public void setComandoSql(Statement comandoSql) {
		this.comandoSql = comandoSql;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
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

	public void closeConectionApartment(Connection conn) {
		try {
			conn.close();
			System.out.println("Conex�o fechada: " + conn);
			
		} catch(SQLException e) {
			System.out.println("Erro para fechar a conex�o: " + e);

		}
	}
	
	public void insertAppartment(String cnpj) {
		try {
			connectingAppartment();
			
			conn = connectingAppartment();
			comandoSql = conn.createStatement();
			comandoSql.executeUpdate("insert into apartamentos values(" + appartment + ", " + floor + ", " + finalNumber + ", " + block + ", " + cnpj + ")");
			System.out.println("Dados inseridos com sucesso: " + conn);
			
			closeConectionApartment(conn);
			
		} catch(SQLException e) {
			System.out.println("Erro na inser��o: " + e);
		}
	}
	
	// Selects
	public void selectAppartment(int floor) {
		int i = 0;
		try {
			connectingAppartment();
			
			conn = connectingAppartment();
			comandoSql = conn.createStatement();
			rs = comandoSql.executeQuery("select count(*) from apartamentos where cnpj = '20' and apartamento between " + floor*10 + " and " + floor*20);
			
			if(rs.next()) {
				totalAppartmentThisFloor = rs.getInt("count(*)");
			}
			
			modelAppartamentThisFloors = new ModelAppartamentThisFloor[totalAppartmentThisFloor];
			rs = comandoSql.executeQuery("select * from apartamentos where cnpj = '20' and apartamento between " + floor*10 + " and " + floor*20);
			
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
			
			closeConectionApartment(conn);
			
			
		} catch (SQLException e) {
			System.out.println("Erro do select: " + e);
		}
	}
	
	public void selectFloor(int appartment) {
		try {
			connectingAppartment();
			
			conn = connectingAppartment();
			comandoSql = conn.createStatement();
			rs = comandoSql.executeQuery("select * from apartamentos where cnpj = '20' and apartamento = " + appartment);
			
			while(rs.next()) {
				this.floor = rs.getInt("andar");
			}
			
			closeConectionApartment(conn);
			
			System.out.println("Select executado com sucesso: " + conn);
			
		} catch (SQLException e) {
			System.out.println("Select error: " + e);
		}
	}
}
