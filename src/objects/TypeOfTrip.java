package objects;

public class TypeOfTrip {
	private int keyTrip;
	private String typeOfTrip;
	
	public TypeOfTrip(int keyTrip, String typeOfTrip) {
		this.keyTrip = keyTrip;
		this.typeOfTrip = typeOfTrip;
	}
	
	public int getKeyTrip() {
		return keyTrip;
	}
	public void setKeyTrip(int keyTrip) {
		this.keyTrip = keyTrip;
	}
	public String getTypeOfTrip() {
		return typeOfTrip;
	}
	public void setTypeOfTrip(String typeOfTrip) {
		this.typeOfTrip = typeOfTrip;
	}

}
