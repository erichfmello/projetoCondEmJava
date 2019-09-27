package Controler;

import java.util.Date;
import Model.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ControlerRentalCommunAreas extends ControlerConection {
	private Date reservationDate;
	private String cnpj;
	private int reservationAppartament;
	
	// Construtores
	public ControlerRentalCommunAreas() {
	}

	public ControlerRentalCommunAreas(Date reservationDate, String cnpj, int reservationAppartament) {
		super();
		this.reservationDate = reservationDate;
		this.cnpj = cnpj;
		this.reservationAppartament = reservationAppartament;
	}

	// getter and setter
	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public int getReservationAppartament() {
		return reservationAppartament;
	}

	public void setReservationAppartament(int reservationAppartament) {
		this.reservationAppartament = reservationAppartament;
	}
	
	// Selects
	
	// Inserts
	public void insertReservation(String cnpj) {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		
		
		try {
			conecting();
			
			conn = conecting();
			comandSql = conn.createStatement();
			comandSql.executeUpdate("insert into alugado values('" + df.format(reservationDate) + "', '" + cnpj + "', " + reservationAppartament + ")");
			
			closeConection(conn);
			
		} catch (SQLException e) {
			System.out.println("Erro no insert: " + e);
		}
	}
}
