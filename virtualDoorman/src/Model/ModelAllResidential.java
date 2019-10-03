package Model;

public class ModelAllResidential {
	private String name;
	private String cnpj;
	
	// construtores
	public ModelAllResidential() {
		super();
	}

	public ModelAllResidential(String name, String cnpj) {
		this.name = name;
		this.cnpj = cnpj;
	}

	// getter and setter
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
