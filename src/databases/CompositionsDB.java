package databases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Composition;
import objects.Hotel;

public class CompositionsDB {
	private Connection con;
	
	private static final String SELECT_ALL_COMPS = "select * from compositions";
	private static final String SELECT_HOTEL_WITH_MAX_COMPOSITION = "select Key_Hotel, Hotel_Name, Hotel_Address, max(Count_Composition) as Max_Composition "
																	+ "from ("
																	+ "select Key_Hotel, Hotel_Name, Hotel_Address,  count(*) as Count_Composition "
																	+ "from ("
																	+ "select Key_Hotel, Hotel_Name, Hotel_Address, Key_Composition "
																	+ "from ("
																	+ "select reviews.Key_Hotel, hotels.Hotel_Name, hotels.Key_Country as Key_Hotel_Country, hotels.Hotel_Address, reviews.Key_Composition "
																	+ "from reviews "
																	+ "left join hotels "
																	+ "ON (reviews.Key_Hotel = hotels.Key_Hotel)) "
																	+ "as reviews_with_hotels "
																	+ "where Key_Hotel_Country = ? and Key_Composition = ?) "
																	+ "as hotels_of_composition "
																	+ "group by Key_Hotel "
																	+ "order by Count_Composition desc) "
																	+ "as max_composition";
	public CompositionsDB(Connection con) {
		this.con = con;
	}

	public ArrayList<Composition> getAllComp() throws SQLException {
		ArrayList<Composition> compList = new ArrayList<>();
		try {
			String query = SELECT_ALL_COMPS;
			PreparedStatement pst = this.con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				int Key_Composition = Integer.parseInt(rs.getString("Key_Composition"));
				String composition = rs.getString("Composition");
				Composition type = new Composition(Key_Composition, composition);
				compList.add(type);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return compList;
	}
	
	public Hotel getHotelWithMaxComposition(int keyCountry, int keyComposition){
		Hotel hotel = null;
		try {
            String query = SELECT_HOTEL_WITH_MAX_COMPOSITION;
            PreparedStatement pst = this.con.prepareStatement(query);
            pst.setInt(1, keyCountry);
            pst.setInt(2, keyComposition);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				hotel = new Hotel(rs.getInt("Key_Hotel"),rs.getString("Hotel_Name"),rs.getString("Hotel_Address"));
			}      
		}catch(SQLException e){
	            e.printStackTrace();
	    } 
	        return hotel;
	}
}//end