package Model;

public class ModelRentalModel {
	private String cnpj;
	private String description;
	private double price;
	
	// Construtores
	public ModelRentalModel() {
	}

	public ModelRentalModel(String cnpj, String description, double price) {
		super();
		this.cnpj = cnpj;
		this.description = description;
		this.price = price;
	}

	// getter and setter
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
