package Model;

public class ModelCommunArea {
	private String name;
	private String cnpj;
	
	// Construtores
	public ModelCommunArea() {
	}

	public ModelCommunArea(String name, String cnpj) {
		super();
		this.name = name;
		this.cnpj = cnpj;
	}	

	// Getter and setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
}
