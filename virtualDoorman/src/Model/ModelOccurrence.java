package Model;

import java.util.Date;

public class ModelOccurrence {
	private Date date;
	private int appartament;
	private String description;
	private String cnpj;
	
	// Contrutores
	public ModelOccurrence() {
	}

	public ModelOccurrence(Date date, int appartament, String description, String cnpj) {
		this.date = date;
		this.appartament = appartament;
		this.description = description;
		this.cnpj = cnpj;
	}

	// getter and setter
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getAppartament() {
		return appartament;
	}

	public void setAppartament(int appartament) {
		this.appartament = appartament;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}	
}
