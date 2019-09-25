package Controler;

import java.sql.*;
import java.util.Date;

import Model.*;

public class ControlerAllGuest extends ControlerConection {
	private String documentGuest;
    private String name;
    
    private Date date;
    
    private int totalGuest;
    
    // Variaveis Model
    ModelRentalGuest[] modelRentalGuest;
    
    // Contrutores
    public ControlerAllGuest() {
	}

	public ControlerAllGuest(String documentGuest, String name) {
		super();
		this.documentGuest = documentGuest;
		this.name = name;
	}
	
	public ControlerAllGuest(Date date) {
		super();
		this.date = date;
	}

	// getter and setter
	public String getDocumentGuest() {
		return documentGuest;
	}

	public void setDocumentGuest(String documentGuest) {
		this.documentGuest = documentGuest;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
		
	// Selects
	public void selectAllGuestDate() {
		int i = 0;
		
		try {
			conecting();
			
			conn = conecting();
			comandSql = conn.createStatement();
			rs = comandSql.executeQuery("select count(*) from convidado c inner join alugadoConvidado ac on c.documentoConvidado = ac.documentoConvidado where dataAluguel = " + date);
			if(rs.next()) {
				totalGuest = rs.getInt("count(*)");
			}
			modelRentalGuest = new ModelRentalGuest[totalGuest];
						
			rs = comandSql.executeQuery("select * from convidado c inner join alugadoConvidado ac on c.documentoConvidado = ac.documentoConvidado where dataAluguel = " + date);
			while(rs.next()) {
				date = rs.getDate("dataAluguel");
				documentGuest = rs.getString("documentoConvidado");
				
				modelRentalGuest[i] = new ModelRentalGuest(date, documentGuest);
				
				i++;
			}
				
			closeConection(conn);
			
		} catch (SQLException e) {
			System.out.println("Erro no select: " + e);
		}
	}

	// Inserts
	public void insertGuest() {
		try {
			conecting();
			
			conn = conecting();
			comandSql = conn.createStatement();
			comandSql.executeUpdate("insert into convidado values ('" + documentGuest + "', '" + name + "')");
			
			closeConection(conn);
			
		} catch (SQLException e) {
			System.out.println("Erro no insert: " + e);
			
		}
	}
	
}