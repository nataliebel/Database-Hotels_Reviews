package databases;

import java.sql.*;
import java.util.ArrayList;

import objects.Country;

public class CountriesDB {
	private Connection con;
	
	private static final String SELECT_COUNTRY_BY_KEY = "select Country from countries where Key_Country = ";
	private static final String SELECT_ALL_COUNTRIES = "select * from countries";
	private static final String SELECT_EUROPEAN_COUNTRIES = "select * from countries where In_Europe = 1";
	private static final String SELECT_HOTELS_COUNTRIES = "select distinct countries.Key_Country, countries.Country FROM countries right join "
														+ "(select Key_Hotel, Hotel_Name, hotels.Key_Country, Country, Hotel_Address from hotels left join countries on (hotels.Key_Country = countries.Key_Country)) "
														+ "as hotels_and_countries "
														+ "on (countries.Key_Country = hotels_and_countries.Key_Country)";
	private static final String SELECT_REVIEWERS_COUNTRIES = "select distinct users_with_keyCountries.Key_Country, countries.Country "
															+ "from countries right join ("
															+ "select distinct users.Key_Country "
															+ "from users left join reviews "
															+ "on (users.Key_User = reviews.Key_User)) "
															+ "as users_with_keyCountries "
															+ "on (countries.Key_Country = users_with_keyCountries.Key_Country) "
															+ "order by countries.Country asc";
;

	public CountriesDB(Connection con) {
		this.con = con;
	}

	public String getCountryByKey(int keyCountry) throws SQLException {
		String countryName = null;
		try {
			String query = SELECT_COUNTRY_BY_KEY + keyCountry;
			PreparedStatement pst = this.con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				countryName = rs.getString("Country");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return countryName;
	}

	public ArrayList<Country> getAllCountries() throws SQLException {
		ArrayList<Country> countriesList = new ArrayList<>();
		try {
			String query = SELECT_ALL_COUNTRIES;
			PreparedStatement pst = this.con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				int keyCountry = Integer.parseInt(rs.getString("Key_Country"));
				String countryName = rs.getString("Country");
				// boolean isEurope = rs.getBoolean("Is_Europe");
				Country country = new Country(keyCountry, countryName);
				countriesList.add(country);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return countriesList;
	}

	public ArrayList<Country> getEuropeanCountries() throws SQLException {
		ArrayList<Country> europeanCountriesLst = new ArrayList<>();
		try {
			String query = SELECT_EUROPEAN_COUNTRIES;
			PreparedStatement pst = this.con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				int keyCountry = Integer.parseInt(rs.getString("Key_Country"));
				String countryName = rs.getString("Country");
				Country country = new Country(keyCountry, countryName);
				europeanCountriesLst.add(country);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return europeanCountriesLst;
	}
	
	public ArrayList<Country> getHotelsCountries() throws SQLException {
		ArrayList<Country> hotelsCountriesList = new ArrayList<>();
		try {
			String query = SELECT_HOTELS_COUNTRIES;
			PreparedStatement pst = this.con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				int keyCountry = Integer.parseInt(rs.getString("Key_Country"));
				String countryName = rs.getString("Country");
				Country country = new Country(keyCountry, countryName);
				hotelsCountriesList.add(country);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return hotelsCountriesList;
	}
	
	public ArrayList<Country> getReviewersCountries() throws SQLException {
		ArrayList<Country> reviewersCountriesList = new ArrayList<>();
		try {
			String query = SELECT_REVIEWERS_COUNTRIES;
			PreparedStatement pst = this.con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				int keyCountry = Integer.parseInt(rs.getString("Key_Country"));
				String countryName = rs.getString("Country");
				Country country = new Country(keyCountry, countryName);
				reviewersCountriesList.add(country);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return reviewersCountriesList;
	}

}
