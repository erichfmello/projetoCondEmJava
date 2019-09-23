package Model;

public class ModelOwner {
	private String cpf;
	private String name;
	private String rg;
	private String ddd;
	private String phone;
	private String email;
	
	// Construtores	
	public ModelOwner() {
	}

	public ModelOwner(String cpf, String name, String rg, String ddd, String phone, String email) {
		this.cpf = cpf;
		this.name = name;
		this.rg = rg;
		this.ddd = ddd;
		this.phone = phone;
		this.email = email;
	}
	
	public ModelOwner(String cpf, String name) {
		this.cpf = cpf;
		this.name = name;
	}

	// Getter and setter
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
