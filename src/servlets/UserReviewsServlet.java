//package servlets;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import connections.ConnectionPro;
//import databases.*;
//import objects.*;
//
///**
// * Servlet implementation class UserReviewsServlet
// */
//@WebServlet("/UserReviewsServlet")
//public class UserReviewsServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	/**
//	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//	 * methods.
//	 *
//	 * @param request  servlet request
//	 * @param response servlet response
//	 * @throws ServletException if a servlet-specific error occurs
//	 * @throws IOException      if an I/O error occurs
//	 */
//	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		response.setContentType("text/html;charset=UTF-8");
//		try (PrintWriter out = response.getWriter()) {
//			/* TODO output your page here. You may use following sample code. */
//			out.println("<!DOCTYPE html>");
//			out.println("<html>");
//			out.println("<head>");
//			out.println("<title>Servlet UserReviewsServlet</title>");
//			out.println("</head>");
//			out.println("<body>");
//
//			HttpSession session = request.getSession();
//			User user = (User) session.getAttribute("User");
//
//			ReviewsDB reviewsDB = new ReviewsDB(ConnectionPro.getConnection());
//			ArrayList<Review> userReviews = reviewsDB.getReviewsByUser(user.getKeyUser());
//
//			if (userReviews != null) {
//				out.println("not null");
//				session.setAttribute("getReviewsByUser", userReviews);
//				response.sendRedirect("userReviewsList.jsp");
//			} else {
//				out.println("something went wrong");
//			}
//
//			out.println("</body>");
//			out.println("</html>");
//		}
//	}
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
//	 *      response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		processRequest(request, response);
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
//	 *      response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		processRequest(request, response);
//	}
//
//}
