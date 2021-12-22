package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connections.ConnectionPro;
import databases.*;

/**
 * Servlet implementation class DeleteAllUserReviewsServlet
 */
@WebServlet("/DeleteAllUserReviewsServlet")
public class DeleteAllUserReviewsServlet extends HttpServlet {
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
			out.println("<title>Servlet DeleteAllUserReviewsServlet</title>");
			out.println("</head>");
			out.println("<body>");

			int keyUser = Integer.parseInt(request.getParameter("Key_User"));
			
			ReviewsDB reviewsDB = new ReviewsDB(ConnectionPro.getConnection());
			boolean isdeleted = reviewsDB.deleteAllUserReviews(keyUser);
			
			if(isdeleted) {
				out.println("YOUR REVIEWS WERE DELETED");	
			} else {
				out.println("something went wrong");
			}

			out.println("</body>");
			out.println("</html>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
