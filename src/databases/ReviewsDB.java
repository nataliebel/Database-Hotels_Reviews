package databases;

import java.sql.*;
import java.util.ArrayList;

import objects.Review;

import java.sql.PreparedStatement;

public class ReviewsDB {

	private Connection con = null;

	private static final String SELECT_ALL_REVIEWS = "select * from reviews";
	private static final String SELECT_REVIEW_BY_USER = "SELECT Key_Review, Key_User, User_Country, Hotel_Name, countries.Country AS Hotel_Country, Hotel_Address, Date, Negative_Review, Positive_Review, Reviewer_Score, Type_Of_Trip, Composition, Num_Of_Nights "
														+ "FROM countries RIGHT JOIN "
														+ "(SELECT Key_Review, Key_User, countries.Country AS User_Country, Hotel_Name, Key_Hotel_Country, Hotel_Address, Date, Negative_Review, Positive_Review, Reviewer_Score, Type_Of_Trip, Composition, Num_Of_Nights "
														+ "FROM countries RIGHT JOIN "
														+ "(SELECT reviews.Key_Review, reviews.Key_User, users.Key_Country AS Key_User_Country, hotels.Hotel_Name, hotels.Key_Country AS Key_Hotel_Country, hotels.Hotel_Address, reviews.Date, reviews.Negative_Review, reviews.Positive_Review, reviews.Reviewer_Score, typeoftrip.Type_Of_Trip, compositions.Composition, reviews.Num_Of_Nights "
														+ "FROM reviews LEFT JOIN typeOfTrip ON (reviews.Key_Trip = typeOfTrip.Key_Trip) "
														+ "LEFT JOIN compositions ON (reviews.Key_Composition = compositions.Key_Composition) "
														+ "LEFT JOIN hotels ON (reviews.Key_Hotel = hotels.Key_Hotel) "
														+ "LEFT JOIN users ON (reviews.Key_User = users.Key_User)) " 
														+ "AS full_reviews_with_key_Countries "
														+ "ON (countries.Key_Country = full_reviews_with_key_Countries.Key_User_Country)) "
														+ "AS full_reviews_with_user_country "
														+ "ON (countries.Key_Country = full_reviews_with_User_Country.Key_Hotel_Country) WHERE Key_User = ?";
	private static final String SELECT_REVIEW_BY_HOTEL = "SELECT Key_Review, Key_User, Hotel_Name,  countries.Country AS Hotel_Country, Hotel_Address, Date, Negative_Review, Positive_Review, Reviewer_Score, Type_Of_Trip, Composition, Num_Of_Nights "
														+ "FROM countries RIGHT JOIN ( "
														+ "SELECT reviews.Key_Review, reviews.Key_User, users.Key_Country AS Key_User_Country, hotels.Key_Hotel, hotels.Hotel_Name, hotels.Key_Country AS Key_Hotel_Country, hotels.Hotel_Address, reviews.Date, reviews.Negative_Review, reviews.Positive_Review, reviews.Reviewer_Score, typeoftrip.Type_Of_Trip, compositions.Composition, reviews.Num_Of_Nights "
														+ "FROM reviews "
														+ "LEFT JOIN typeOfTrip "
														+ "ON (reviews.Key_Trip = typeOfTrip.Key_Trip) "
														+ "LEFT JOIN compositions "
														+ "ON (reviews.Key_Composition = compositions.Key_Composition) "
														+ "LEFT JOIN hotels "
														+ "ON (reviews.Key_Hotel = hotels.Key_Hotel) "
														+ "LEFT JOIN users "
														+ "ON (reviews.Key_User = users.Key_User) "
														+ ") AS full_reviews_with_key_Countries "
														+ "ON (countries.Key_Country = full_reviews_with_key_Countries.Key_Hotel_Country) "
														+ "WHERE Key_Hotel = ?";
	private static final String INSERT_REVIEW = "insert into reviews(Key_User, Key_Hotel, Date, Negative_Review, Positive_Review, Reviewer_Score, Key_Trip, Key_Composition, Num_Of_Nights) values(?,?,?,?,?,?,?,?,?)";
	private static final String DELETE_REVIEW_SQL = "delete from reviews where Key_Review = ?";
	private static final String DELETE_USER_REVIEWS = "delete from reviews where Key_User = ?";

	public ReviewsDB(Connection con) {
		this.con = con;
	}

	public ReviewsDB() {
	}

	public ArrayList<Review> getAllReviews() throws SQLException {
		ArrayList<Review> reviewsList = new ArrayList<>();
		try {
			String query = SELECT_ALL_REVIEWS;
			PreparedStatement pst = this.con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				int keyReview = rs.getInt("Key_Review");
				int keyUser = rs.getInt("Key_User");
				String hotelName = rs.getString("Hotel_Name");
				String date = rs.getString("Date");
				String negReview = rs.getString("Negative_Review");
				String posReview = rs.getString("Positive_Review");
				float reviewerScore = rs.getFloat("Reviewer_Score");
				String trip = rs.getString("Type_Of_Trip");
				String comp = rs.getString("Composition");
				int numOfNights = rs.getInt("Num_Of_Nights");

				Review review = new Review(keyReview, keyUser, hotelName, date, negReview, posReview, reviewerScore, trip,
						comp, numOfNights);
				reviewsList.add(review);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return reviewsList;
	}

	public ArrayList<Review> getReviewsByUser(int keyUser) {
		ArrayList<Review> revByUserList = new ArrayList<Review>();
		Review revByUser = null;
		try {
			String query = SELECT_REVIEW_BY_USER;
			PreparedStatement pst = this.con.prepareStatement(query);
			pst.setInt(1, keyUser);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				int keyReview = rs.getInt("Key_Review");
				int keyHotel = rs.getInt("Key_Hotel");
				String date = rs.getString("Date");
				String hotelName = rs.getString("Hotel_Name");
				String negReview = rs.getString("Negative_Review");
				String posReview = rs.getString("Positive_Review");
				float reviewerScore = rs.getFloat("Reviewer_Score");
				int keyTrip = rs.getInt("Key_Trip");
				int keyComp = rs.getInt("Key_Composition");
				int numOfNights = rs.getInt("Num_Of_Nights");
				revByUser = new Review(keyReview, keyUser, keyHotel, hotelName,date, negReview, posReview, reviewerScore, keyTrip,
						keyComp, numOfNights);
				revByUserList.add(revByUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return revByUserList;
	}
	
	public ArrayList<Review> getReviewsByHotel(int keyHotel) {
		ArrayList<Review> revByUserList = new ArrayList<Review>();
		Review revByUser = null;
		try {
			String query = SELECT_REVIEW_BY_HOTEL;
			PreparedStatement pst = this.con.prepareStatement(query);
			pst.setInt(1, keyHotel);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				int keyReview = rs.getInt("Key_Review");
				int keyUser = rs.getInt("Key_User");
				String hotelName = rs.getString("Hotel_Name");
				String hotelAddress = rs.getString("Hotel_Address");
				String date = rs.getString("Date");
				String negReview = rs.getString("Negative_Review");
				String posReview = rs.getString("Positive_Review");
				float reviewerScore = rs.getFloat("Reviewer_Score");
				String typeOfTrip = rs.getString("Type_Of_Trip");
				String composition = rs.getString("Composition");
				int numOfNights = rs.getInt("Num_Of_Nights");
				
				revByUser = new Review(keyReview, keyUser, keyHotel, hotelName,hotelAddress ,date, negReview, posReview, reviewerScore, typeOfTrip,
						composition, numOfNights);
				revByUserList.add(revByUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return revByUserList;
	}

	public boolean deleteReview(int keyReview) throws SQLException {
		boolean rowDeleted = false;
		try {
			String query = DELETE_REVIEW_SQL;
			PreparedStatement pt = this.con.prepareStatement(query);
			pt.setInt(1, keyReview);
			rowDeleted = pt.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowDeleted;
	}
	
	public boolean deleteAllUserReviews(int keyUser) throws SQLException {
		boolean isReviewsDeleted = false;
		try {
			String query = DELETE_USER_REVIEWS;
			PreparedStatement pt = this.con.prepareStatement(query);
			pt.setInt(1, keyUser);
			isReviewsDeleted = pt.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isReviewsDeleted;
	}
	// TODO: maybe delete because similar to insertReview
	public boolean saveReview(Review review) {
		boolean set = false;
		try {

			// Insert register data to database
			String query = INSERT_REVIEW;

			PreparedStatement pt = this.con.prepareStatement(query);

			pt.setInt(1, review.getKeyUser());
			pt.setInt(2, review.getKeyHotel());
			pt.setString(3, review.getDate());
			pt.setString(4, review.getNegativeReview());
			pt.setString(5, review.getPositiveReview());
			pt.setDouble(6, review.getReviewerScore());
			pt.setInt(7, review.getKeyTrip());
			pt.setInt(8, review.getKeyComposition());
			pt.setInt(9, review.getNumOfNights());

			pt.executeUpdate();

			set = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return set;
	}
}
