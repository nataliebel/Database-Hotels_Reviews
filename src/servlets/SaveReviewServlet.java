package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connections.ConnectionPro;
import databases.ReviewsDB;
import objects.Review;

@WebServlet("/SaveReviewServlet")
public class SaveReviewServlet extends HttpServlet {
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
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet SaveReviewServlet</title>");
			out.println("</head>");
			out.println("<body>");
			
			// user name - set key user
			int keyuser = Integer.parseInt(request.getParameter("Key_User"));

			// key hotel of review
			int keyhotel = Integer.parseInt(request.getParameter("Key_Hotel"));

			// date
			String date = request.getParameter("Date");

			// negative rev
			String negRev = request.getParameter("Negative_Review");

			// positive rev
			String posRev = request.getParameter("Positive_Review");

			// reviewer score
			Float score = Float.parseFloat(request.getParameter("Reviewer_Score"));

			// key trip
			int keyTrip = Integer.parseInt(request.getParameter("Key_Trip"));

			// key comp
			int keyComp = Integer.parseInt(request.getParameter("Key_Composition"));

			// num of night
			int numOfNights = Integer.parseInt(request.getParameter("Num_Of_Nights"));

			// make review object
			Review reviewModel = new Review(keyuser, keyhotel, date, negRev, posRev, score, keyTrip, keyComp,
					numOfNights);
						
			ReviewsDB insertReview = new ReviewsDB(ConnectionPro.getConnection());
			boolean isAdded = insertReview.saveReview(reviewModel);
		
			if (isAdded) {
				response.sendRedirect("reviewWasAdded.jsp");
			} else {
				out.println("something went wrong");
			}

			out.println("</body>");
			out.println("</html>");
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}
