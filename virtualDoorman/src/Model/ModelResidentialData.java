package Model;

public class ModelResidentialData {
	private String name, address, cep, neighborhood, uf, cnpj;
	private int number, appartmentLastFloor, floor, appartmentPerFloor, Block;
	private int totalAppartments;

	public ModelResidentialData() {
	}

	public ModelResidentialData(String name, String address, String cep, String neighborhood, String uf, String cnpj,
			int number, int appartmentLastFloor, int floor, int appartmentPerFloor, int block) {
		this.name = name;
		this.address = address;
		this.cep = cep;
		this.neighborhood = neighborhood;
		this.uf = uf;
		this.cnpj = cnpj;
		this.number = number;
		this.appartmentLastFloor = appartmentLastFloor;
		this.floor = floor;
		this.appartmentPerFloor = appartmentPerFloor;
		Block = block;
		this.totalAppartments = floor * appartmentPerFloor * block + appartmentLastFloor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getAppartmentLastFloor() {
		return appartmentLastFloor;
	}

	public void setAppartmentLastFloor(int appartmentLastFloor) {
		this.appartmentLastFloor = appartmentLastFloor;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getAppartmentPerFloor() {
		return appartmentPerFloor;
	}

	public void setAppartmentPerFloor(int appartmentPerFloor) {
		this.appartmentPerFloor = appartmentPerFloor;
	}

	public int getBlock() {
		return Block;
	}

	public void setBlock(int block) {
		Block = block;
	}

	public int getTotalAppartments() {
		return totalAppartments;
	}

	public void setTotalAppartments(int totalAppartments) {
		this.totalAppartments = totalAppartments;
	}
}
