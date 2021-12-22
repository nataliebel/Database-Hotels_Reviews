package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import objects.*;
import databases.*;

/**
 * Servlet implementation class UserServlets
 */
@WebServlet("/UserReviewServlet")
public class UserReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReviewsDB reviewsDB;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public void init() {
		reviewsDB = new ReviewsDB();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();

		try {
			switch (action) {
//			case "/new":
//				showNewForm(request, response);
//				break;
//			case "/insert":
//				insertNewReview(request, response);
//				break;
			case "/delete":
				deleteReview(request, response);
				break;
//			case "/edit":
//				showEditForm(request, response);
//				break;
//			case "/update":
//				updateUser(request, response);
//				break;
			default:
				listOfReview(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

//	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		RequestDispatcher dispatcher = request.getRequestDispatcher("addReview.jsp");
//		dispatcher.forward(request, response);
//	}

//	private void insertNewReview(HttpServletRequest request, HttpServletResponse response)
//			throws SQLException, IOException {
//
//		int keyReview = Integer.parseInt(request.getParameter("Key_Review"));
//		int keyUser = Integer.parseInt(request.getParameter("Key_User"));
//		int keyHotel = Integer.parseInt(request.getParameter("Key_User"));
//		String date = request.getParameter("Date");
//		String negReview = request.getParameter("Negative_Review");
//		String posReview = request.getParameter("Positive_Review");
//		float reviewScore = Float.parseFloat(request.getParameter("Review_Score"));
//		int keyTrip = Integer.parseInt(request.getParameter("Key_Trip"));
//		int keyComp = Integer.parseInt(request.getParameter("Key_Composition"));
//		int numOfNights = Integer.parseInt(request.getParameter("Num_Of_Nights"));
//
//		Review newReview = new Review(keyReview, keyUser, keyHotel, date, negReview, posReview, reviewScore, keyTrip, keyComp, numOfNights);
//		reviewsDB.insertReview(newReview);
//		response.sendRedirect("listReview");
//	}

	private void deleteReview(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int keyReview = Integer.parseInt(request.getParameter("Key_Review"));
		reviewsDB.deleteReview(keyReview);
		response.sendRedirect("listReview");

	}
	
//	private void updateReview(HttpServletRequest request, HttpServletResponse response)
//		    throws SQLException, IOException {
//		        int keyReview = Integer.parseInt(request.getParameter("Key_Review"));
//		        String date = request.getParameter("Date");
//		        String negativeReview = request.getParameter("Negative_Review");
//		        String positiveReview = request.getParameter("Positive_Review");
//
//		        Review review = new Review(keyReview, date, negativeReview, positiveReview);
//		        userDAO.updateUser(book);
//		        response.sendRedirect("list");
//		    }
	
	   private void listOfReview(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, IOException, ServletException {
		   			User user = (User) request.getSession().getAttribute("User");
		   			System.out.println(user.getKeyUser());
			        ArrayList <Review> listOfReviews = reviewsDB.getReviewsByUser(user.getKeyUser());
			        request.setAttribute("listOfReviews", listOfReviews);
			        RequestDispatcher dispatcher = request.getRequestDispatcher("userReviewsList.jsp");
			        dispatcher.forward(request, response);
			    }

}// end class
