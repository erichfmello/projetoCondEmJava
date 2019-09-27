package Model;

import java.util.Date;

public class ModelRentalCommunAreas {
	private Date reservationDate;
	private String cnpj;
	private int reservationAppartament;
	
	// Construtores
	public ModelRentalCommunAreas() {
	}

	public ModelRentalCommunAreas(Date reservationDate, String cnpj, int reservationAppartament) {
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
}
