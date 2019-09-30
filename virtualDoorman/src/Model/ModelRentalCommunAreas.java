package Model;

import java.util.Date;

public class ModelRentalCommunAreas {
	private Date reservationDate;
	private String cnpj;
	private String description;
	private int reservationAppartament;
	
	// Construtores
	public ModelRentalCommunAreas() {
	}

	public ModelRentalCommunAreas(Date reservationDate, String cnpj, String description, int reservationAppartament) {
		this.reservationDate = reservationDate;
		this.cnpj = cnpj;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getReservationAppartament() {
		return reservationAppartament;
	}

	public void setReservationAppartament(int reservationAppartament) {
		this.reservationAppartament = reservationAppartament;
	}
}
