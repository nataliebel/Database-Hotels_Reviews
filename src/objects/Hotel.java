package objects;

public class Hotel {
	private int keyHotel;
	private String hotelName;
	private int keyCountry;
	private String hotelAddress;
	private float avgScore;
	
	public Hotel(int keyHotel, String hotelName, int keyCountry, String hotelAddress) {
		this.keyHotel = keyHotel;
		this.hotelName = hotelName;
		this.keyCountry = keyCountry;
		this.hotelAddress = hotelAddress;
	}
	
	public Hotel(int keyHotel, int keyCountry, String hotelName) {
		this.keyHotel = keyHotel;
		this.keyCountry = keyCountry;
		this.hotelName = hotelName;
	}
	
	public Hotel(int keyHotel, String hotelName, String hotelAddress) {
		this.keyHotel = keyHotel;
		this.hotelName = hotelName;
		this.hotelAddress = hotelAddress;
	}
	
	public Hotel(int keyHotel, String hotelName, int keyCountry, String hotelAddress, float avgScore) {
		this.keyHotel = keyHotel;
		this.hotelName = hotelName;
		this.keyCountry = keyCountry;
		this.hotelAddress = hotelAddress;
		this.setAvgScore(avgScore);
	}
	
	public int getKeyHotel() {
		return keyHotel;
	}
	public void setKeyHotel(int keyHotel) {
		this.keyHotel = keyHotel;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public int getKeyCountry() {
		return keyCountry;
	}
	public void setKeyCountry(int keyCountry) {
		this.keyCountry = keyCountry;
	}
	public String getHotelAddress() {
		return hotelAddress;
	}
	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}

	public float getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(float avgScore) {
		this.avgScore = avgScore;
	}

}
