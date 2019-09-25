package Model;

public class ModelGuest {
	private String documentGuest;
    private String name;
    
    // Construtores
    public ModelGuest() {
	}

	public ModelGuest(String documentGuest, String name) {
		this.documentGuest = documentGuest;
		this.name = name;
	}

	// getter and setter
	public String getDocumentGuest() {
		return documentGuest;
	}

	public void setDocumentGuest(String documentGuest) {
		this.documentGuest = documentGuest;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}