package Model;

public class ModelAppartamentThisFloor {
	private int appartment;
	private int floor;
	private int finalFloor;
	private String block;
	private String cnpj;
	
	public ModelAppartamentThisFloor() {
	}

	public ModelAppartamentThisFloor(int appartment, int floor, int finalFloor, String block, String cnpj) {
		super();
		this.appartment = appartment;
		this.floor = floor;
		this.finalFloor = finalFloor;
		this.block = block;
		this.cnpj = cnpj;
	}

	public int getAppartment() {
		return appartment;
	}

	public void setAppartment(int appartment) {
		this.appartment = appartment;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getFinalFloor() {
		return finalFloor;
	}

	public void setFinalFloor(int finalFloor) {
		this.finalFloor = finalFloor;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
}