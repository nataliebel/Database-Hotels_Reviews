package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connections.*;
import databases.*;

@WebServlet("/ReviewersByPercentageServlet")
public class ReviewersByPercentageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			/* TODO output your page here. You may use following sample code. */
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet ReviewersByPercentageServlet</title>");
			out.println("</head>");
			out.println("<body>");

			int keyHotel = Integer.parseInt(request.getParameter("Key_Hotel"));
			
			HotelsDB hotelsDB = new HotelsDB(ConnectionPro.getConnection());
			Map<String, Float> countriesPercentageMap = hotelsDB.getPercentageOfReviewersCountries(keyHotel);
			
			if(countriesPercentageMap != null) {
				out.println("not null");
				HttpSession session = request.getSession();
				session.setAttribute("getPercentageOfReviewersCountries", countriesPercentageMap);
				response.sendRedirect("percentageOfCountries.jsp");	
			} else {
				out.println("something went wrong");
			}

			out.println("</body>");
			out.println("</html>");
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
