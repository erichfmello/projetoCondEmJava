package Model;

import java.util.Date;

public class ModelRentalGuest {
	private Date date;
	private String documentGuest;
	
	// Construtores
	public ModelRentalGuest() {
	}
	
	public ModelRentalGuest(Date date, String documentGuest) {
		this.date = date;
		this.documentGuest = documentGuest;
	}

	// getter and setter
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDocumentGuest() {
		return documentGuest;
	}

	public void setDocumentGuest(String documentGuest) {
		this.documentGuest = documentGuest;
	}	
}