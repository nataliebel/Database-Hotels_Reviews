package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import connections.ConnectionPro;
import databases.CountriesDB;
import databases.HotelsDB;
import objects.Country;
import objects.Hotel;

/**
 * Servlet implementation class CountryHotelServlet
 */
@WebServlet("/CountryHotelServlet")
public class CountryHotelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CountryHotelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {

			CountriesDB countriesDB = new CountriesDB(ConnectionPro.getConnection());
			HotelsDB hotelsDB = new HotelsDB(ConnectionPro.getConnection());
            
            String op = request.getParameter("operation");
            
            if (op.equals("country")) {
                ArrayList<Country> cList = countriesDB.getHotelsCountries();
                JSONArray jsonCList = new JSONArray(cList);
                String countriesList = jsonCList.toString();
               
                response.setContentType("text/html");
                response.getWriter().write(countriesList);
            }

            if (op.equals("hotel")) {
                int id = Integer.parseInt(request.getParameter("id"));
                List<Hotel> hList = hotelsDB.getHotelsByKeyCountry(id);
                JSONArray jsonHList = new JSONArray(hList);
                String hotelsList = jsonHList.toString();
                              
                response.setContentType("text/html");
                response.getWriter().write(hotelsList);
            }
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
