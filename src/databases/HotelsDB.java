package databases;

import java.sql.*;
import java.util.*;

import objects.*;


public class HotelsDB {
	private Connection con;

	private static final String SELECT_ALL_HOTELS = "select * from hotels";
	private static final String SELECT_HOTELS_BY_KEY_COUNTRY = "select * from hotels where Key_Country = ?";
	private static final String SELECT_TOP_HOTELS_FROM_EUROPE = "SELECT Key_Hotel, Hotel_Name, Key_Hotel_Country, Hotel_Address, Avg_Reviewer_Score "
																+ "FROM ("
																+ "SELECT Key_Hotel, Hotel_Name, Key_Hotel_Country, countries.Country AS Hotel_Country, Hotel_Address, AVG(Reviewer_Score) AS Avg_Reviewer_Score "
																+ "FROM countries RIGHT JOIN ("
																+ "SELECT Key_Review, Key_User, Key_User_Country, countries.Country AS User_Country, Key_Hotel, Hotel_Name, Key_Hotel_Country, Hotel_Address, Date, Negative_Review, Positive_Review, Reviewer_Score, Type_Of_Trip, Composition, Num_Of_Nights "
																+ "FROM countries RIGHT JOIN ("
																+ "SELECT reviews.Key_Review, reviews.Key_User, users.Key_Country AS Key_User_Country, reviews.Key_Hotel, hotels.Hotel_Name, hotels.Key_Country AS Key_Hotel_Country, hotels.Hotel_Address, reviews.Date, reviews.Negative_Review, reviews.Positive_Review, reviews.Reviewer_Score, typeoftrip.Type_Of_Trip, compositions.Composition, reviews.Num_Of_Nights "
																+ "FROM reviews LEFT JOIN typeOfTrip " + "ON (reviews.Key_Trip = typeOfTrip.Key_Trip) "
																+ "LEFT JOIN compositions " + "ON (reviews.Key_Composition = compositions.Key_Composition) "
																+ "LEFT JOIN hotels " + "ON (reviews.Key_Hotel = hotels.Key_Hotel) " + "LEFT JOIN users "
																+ "ON (reviews.Key_User = users.Key_User)) " + "AS full_reviews_with_key_Countries "
																+ "ON (countries.Key_Country = full_reviews_with_key_Countries.Key_User_Country)) "
																+ "AS full_reviews_with_User_Country "
																+ "ON (countries.Key_Country = full_reviews_with_User_Country.Key_Hotel_Country) "
																+ "GROUP BY Key_Hotel) AS avg_hotels_score " + "ORDER BY Avg_Reviewer_Score DESC " + "LIMIT ?";
	private static final String SELECT_TOP_HOTELS_FROM_COUNTRY = "SELECT Key_Hotel, Hotel_Name, Key_Hotel_Country, Hotel_Country, Hotel_Address, Avg_Reviewer_Score "
																+ "FROM ("
																+ "SELECT Key_Hotel, Hotel_Name, Key_Hotel_Country, countries.Country AS Hotel_Country, Hotel_Address, AVG(Reviewer_Score) AS Avg_Reviewer_Score "
																+ "FROM countries RIGHT JOIN ("
																+ "SELECT Key_Review, Key_User, Key_User_Country, countries.Country AS User_Country, Key_Hotel, Hotel_Name, Key_Hotel_Country, Hotel_Address, Date, Negative_Review, Positive_Review, Reviewer_Score, Type_Of_Trip, Composition, Num_Of_Nights "
																+ "FROM countries RIGHT JOIN ("
																+ "SELECT reviews.Key_Review, reviews.Key_User, users.Key_Country AS Key_User_Country, reviews.Key_Hotel, hotels.Hotel_Name, hotels.Key_Country AS Key_Hotel_Country, hotels.Hotel_Address, reviews.Date, reviews.Negative_Review, reviews.Positive_Review, reviews.Reviewer_Score, typeoftrip.Type_Of_Trip, compositions.Composition, reviews.Num_Of_Nights "
																+ "FROM reviews " + "LEFT JOIN typeOfTrip " + "ON (reviews.Key_Trip = typeOfTrip.Key_Trip) "
																+ "LEFT JOIN compositions " + "ON (reviews.Key_Composition = compositions.Key_Composition) "
																+ "LEFT JOIN hotels " + "ON (reviews.Key_Hotel = hotels.Key_Hotel) " + "LEFT JOIN users "
																+ "ON (reviews.Key_User = users.Key_User)) " + "AS full_reviews_with_key_Countries "
																+ "ON (countries.Key_Country = full_reviews_with_key_Countries.Key_User_Country)) "
																+ "AS full_reviews_with_User_Country "
																+ "ON (countries.Key_Country = full_reviews_with_User_Country.Key_Hotel_Country) "
																+ "GROUP BY Key_Hotel) " + "AS avg_hotels_score " + "WHERE Key_Hotel_Country = ? "
																+ "ORDER BY Avg_Reviewer_Score DESC " + "LIMIT ?";
	private static final String SELECT_ALL_HOTELS_ASC = "SELECT * FROM hotels ORDER BY Key_Country";
	private static final String SELECT_REVIEWERS_COUNTRIES_PERCENTAGE_FOR_HOTEL = "select Country, count(Country) * 100 / sum(count(Country)) over() as Percent "
																				+ "from (select Key_Hotel, Hotel_Name, hotel_with_users_keyCountries.Key_Country, countries.Country "
																				+ "from countries right join "
																				+ "(select reviews.Key_Hotel, Hotel_Name, reviews.Key_User, users.Key_Country from reviews "
																				+ "left join hotels "
																				+ "ON (reviews.Key_Hotel = hotels.Key_Hotel) "
																				+ "left join users "
																				+ "on (reviews.Key_User = users.Key_User)) "
																				+ "as hotel_with_users_keyCountries "
																				+ "on (hotel_with_users_keyCountries.Key_Country = countries.Key_Country)) "
																				+ "as hotels_with_users_countries "
																				+ "where Key_Hotel = ? "
																				+ "group by Key_Country "
																				+ "order by Percent asc";

	public HotelsDB(Connection con) {
		this.con = con;
	}

	public ArrayList<Hotel> getHotels(){
		ArrayList<Hotel> hotelsList = new ArrayList<Hotel>();
		Hotel hotel = null;
		try {
            String query = SELECT_ALL_HOTELS;
            PreparedStatement pst = this.con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				hotel = new Hotel(rs.getInt("Key_Hotel"), rs.getInt("Key_Country"), rs.getString("Hotel_Name"));
				hotelsList.add(hotel);
			}      
		}catch(SQLException e){
	            e.printStackTrace();
	    } 
	        return hotelsList;
	}
	
	public List<Hotel> getHotelsByKeyCountry(int keyCountry){
		List<Hotel> hotelsList = new ArrayList<>();
		Hotel hotel = null;
		try {
            String query = SELECT_HOTELS_BY_KEY_COUNTRY;
            PreparedStatement pst = this.con.prepareStatement(query);
            pst.setInt(1, keyCountry);
			ResultSet rs = pst.executeQuery();

			
			while (rs.next()) {
				hotel = new Hotel(rs.getInt("Key_Hotel"), rs.getString("Hotel_Name"), rs.getInt("Key_Country"),
						rs.getString("Hotel_Address"));
				hotelsList.add(hotel);
			}      
		}catch(SQLException e){
	            e.printStackTrace();
	    } 
	        return hotelsList;
	}

	/**
	 * top hotels from Europe
	 * 
	 * @param numberTop
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Hotel> getTopHotelsFromEurope(int numberTop) {
		Hotel hotel = null;
		ArrayList<Hotel> hotelslist = new ArrayList<>();
		try {
			String query = SELECT_TOP_HOTELS_FROM_EUROPE;

			PreparedStatement pst = this.con.prepareStatement(query);
			pst.setInt(1, numberTop);
			ResultSet rs = pst.executeQuery();

			if (!rs.next()) {
				return null;
			}
			rs.beforeFirst();
			while (rs.next()) {
				hotel = new Hotel(rs.getInt("Key_Hotel"), rs.getString("Hotel_Name"), rs.getInt("Key_Hotel_Country"),
						rs.getString("Hotel_Address"), rs.getFloat("Avg_Reviewer_Score"));
				hotelslist.add(hotel);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return hotelslist;
	}

	/**
	 * top hotels from specific country
	 * 
	 * @param keyCountry
	 * @param numberTop
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Hotel> getTopHotelsFromCountry(int keyCountry, int numberTop) {
		Hotel hotel = null;
		ArrayList<Hotel> hotelslist = new ArrayList<Hotel>();
		try {
			String query = SELECT_TOP_HOTELS_FROM_COUNTRY;
			PreparedStatement pst = this.con.prepareStatement(query);
			pst.setInt(1, keyCountry);
			pst.setInt(2, numberTop);
			ResultSet rs = pst.executeQuery();

			if (!rs.next()) {
				return null;
			}
			rs.beforeFirst();
			while (rs.next()) {
				hotel = new Hotel(rs.getInt("Key_Hotel"), rs.getString("Hotel_Name"), rs.getInt("Key_Hotel_Country"),
						rs.getString("Hotel_Address"), rs.getFloat("Avg_Reviewer_Score"));
				hotelslist.add(hotel);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return hotelslist;
	}

	/**
	 * order hotels from Europe
	 * 
	 * @param
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Hotel> getAllHotelsAsc() {
		Hotel hotel = null;
		ArrayList<Hotel> hotelsList = new ArrayList<>();
		try {
			String query = SELECT_ALL_HOTELS_ASC;
			PreparedStatement pst = this.con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			if (!rs.next()) {
				return null;
			}
			rs.beforeFirst();
			while (rs.next()) {
				hotel = new Hotel(rs.getInt("Key_Hotel"), rs.getString("Hotel_Name"), rs.getInt("Key_Country"),
						rs.getString("Hotel_Address"));
				hotelsList.add(hotel);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return hotelsList;
	}
	
	public Map<String, Float> getPercentageOfReviewersCountries(int keyHotel){
		Map<String, Float> countriesPercentageMap = new HashMap<String, Float>();
		try {
            String query = SELECT_REVIEWERS_COUNTRIES_PERCENTAGE_FOR_HOTEL;
            PreparedStatement pst = this.con.prepareStatement(query);
            pst.setInt(1, keyHotel);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				countriesPercentageMap.put(rs.getString("Country"), Float.parseFloat(rs.getString("Percent")));
			}      
		}catch(SQLException e){
	            e.printStackTrace();
	    } 
	        return countriesPercentageMap;
	}

}
