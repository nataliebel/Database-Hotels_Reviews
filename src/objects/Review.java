package objects;

public class Review {

	private int keyReview;
	private int keyUser;
	private int keyHotel;
	private String date;
	private String negativeReview;
	private String positiveReview;
	private float reviewerScore;
	private int keyTrip;
	private int keyComposition;
	private int numOfNights;
	private String hotelAddress;
	private String hotelName;
	private String composition;
	private String typeOfTrip;

	public Review(int keyReview, int keyUser, String hotelName, String date, String negativeReview,
			String positiveReview, float reviewScore, String trip, String composition, int numOfNights) {
		this.keyReview = keyReview;
		this.keyUser = keyUser;
		this.date = date;
		this.negativeReview = negativeReview;
		this.positiveReview = positiveReview;
		this.reviewerScore = reviewScore;
		this.numOfNights = numOfNights;
		this.hotelName = hotelName;
		this.composition = composition;
		this.typeOfTrip = trip;
		this.negativeReview = negativeReview;

	}

	public Review(int keyUser, int keyHotel, String date, String negativeReview, String positiveReview,
			float reviewerScore, int keyTrip, int keyComposition, int numOfNights) {
		this.keyUser = keyUser;
		this.keyHotel = keyHotel;
		this.date = date;
		this.negativeReview = negativeReview;
		this.positiveReview = positiveReview;
		this.reviewerScore = reviewerScore;
		this.keyTrip = keyTrip;
		this.keyComposition = keyComposition;
		this.numOfNights = numOfNights;
	}

	public Review(int keyReview, int keyUser, int keyHotel, String hotelName, String hotelAddress, String date,
			String negativeReview, String positiveReview, float reviewScore, String trip, String composition,
			int numOfNights) {
		super();
		this.keyReview = keyReview;
		this.keyUser = keyUser;
		this.date = date;
		this.negativeReview = negativeReview;
		this.positiveReview = positiveReview;
		this.reviewerScore = reviewScore;
		this.numOfNights = numOfNights;
		this.hotelName = hotelName;
		this.composition = composition;
		this.typeOfTrip = trip;
		this.negativeReview = negativeReview;
		this.hotelAddress = hotelAddress;
	}

	public Review(int keyReview, int keyUser, int keyHotel, String hotelName, String date, String negativeReview,
			String positiveReview, float reviewScore, String trip, String composition, int nemOfNights) {
		this.hotelName = hotelName;
		this.keyReview = keyReview;
		this.keyUser = keyUser;
		this.keyHotel = keyHotel;
		this.date = date;
		this.negativeReview = negativeReview;
		this.positiveReview = positiveReview;
		this.reviewerScore = reviewScore;
		this.typeOfTrip = trip;
		this.composition = composition;
		this.numOfNights = nemOfNights;
	}

	public Review(int keyReview, int keyUser, int keyHotel, String date, String hotelName, String negativeReview,
			String positiveReview, float reviewScore, int keyTrip, int keyComposition, int nemOfNights) {
		this.hotelName = hotelName;
		this.keyReview = keyReview;
		this.keyUser = keyUser;
		this.keyHotel = keyHotel;
		this.date = date;
		this.negativeReview = negativeReview;
		this.positiveReview = positiveReview;
		this.reviewerScore = reviewScore;
		this.keyTrip = keyTrip;
		this.keyComposition = keyComposition;
		this.numOfNights = nemOfNights;
	}

	public int getKeyReview() {
		return keyReview;
	}

	public void setKeyReview(int keyReview) {
		this.keyReview = keyReview;
	}

	public int getKeyUser() {
		return keyUser;
	}

	public void setKeyUser(int keyUser) {
		this.keyUser = keyUser;
	}

	public int getKeyHotel() {
		return keyHotel;
	}

	public void setKeyHotel(int keyHotel) {
		this.keyHotel = keyHotel;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNegativeReview() {
		return negativeReview;
	}

	public void setNegativeReview(String negativeReview) {
		this.negativeReview = negativeReview;
	}

	public String getPositiveReview() {
		return positiveReview;
	}

	public void setPositiveReview(String positiveReview) {
		this.positiveReview = positiveReview;
	}

	public float getReviewerScore() {
		return reviewerScore;
	}

	public void setReviewerScore(float reviewerScore) {
		this.reviewerScore = reviewerScore;
	}

	public int getKeyTrip() {
		return keyTrip;
	}

	public void setKeyTrip(int keyTrip) {
		this.keyTrip = keyTrip;
	}

	public int getKeyComposition() {
		return keyComposition;
	}

	public void setKeyComposition(int keyComposition) {
		this.keyComposition = keyComposition;
	}

	public int getNumOfNights() {
		return numOfNights;
	}

	public void setNumOfNights(int numOfNights) {
		this.numOfNights = numOfNights;
	}

	public String getComposition() {
		return composition;
	}

	public void setComposition(String composition) {
		this.composition = composition;
	}

	public String getTrip() {
		return typeOfTrip;
	}

	public void setTrip(String trip) {
		this.typeOfTrip = trip;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelAddress() {
		return hotelAddress;
	}

	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}

}
