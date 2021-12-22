package databases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.TypeOfTrip;

public class TypeOfTripDB {
	private Connection con;

	private static final String SELECT_ALL_TYPES = "select * from typeoftrip";

	public TypeOfTripDB(Connection con) {
		this.con = con;
	}
	
	public ArrayList<TypeOfTrip> getAllTypes() throws SQLException {
		ArrayList<TypeOfTrip> typesList = new ArrayList<>();
		try {
			String query = SELECT_ALL_TYPES;
			PreparedStatement pst = this.con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				int keyTrip = Integer.parseInt(rs.getString("Key_Trip"));
				String typeTrip = rs.getString("Type_Of_Trip");
				TypeOfTrip type= new TypeOfTrip(keyTrip, typeTrip);
				typesList.add(type);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return typesList;
	}
}