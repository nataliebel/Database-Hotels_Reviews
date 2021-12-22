package objects;

public class Country {
	public int keyCountry;
	public String country;
	public boolean IsInEurope;
	
	public Country(int keyCountry, String country, boolean isInEurope) {
		this.keyCountry = keyCountry;
		this.country = country;
		this.IsInEurope = isInEurope;
	}
	public Country(int keyCountry, String country) {
		this.country = country;
		this.keyCountry = keyCountry;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getKeyCountry() {
		return keyCountry;
	}
	public void setKeyCountry(int keyCountry) {
		this.keyCountry = keyCountry;
	}
	public boolean isIsInEurope() {
		return IsInEurope;
	}
	public void setIsInEurope(boolean isInEurope) {
		IsInEurope = isInEurope;
	}
}
