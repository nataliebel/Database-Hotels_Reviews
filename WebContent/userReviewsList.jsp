<%@page import="java.sql.*"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="objects.*"%>
<%@page import="connections.*"%>
<%@page import="databases.*"%>
<%@page import="servlets.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
ArrayList<Review> userReviews = (ArrayList<Review>) session.getAttribute("getReviewsByUser");
if(userReviews == null){
	response.sendRedirect("welcome.jsp");
}
Review r1 = userReviews.get(0);
%>

<html>
<head>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>

<body>
	<form action="UserReviewServlet" method="post">
		<header>
			<nav class="navbar navbar-expand-md navbar-dark"
				style="background-color: tomato">
				<div>
					<a href="welcome.jsp" class="navbar-brand"> Hotels Reviews
						Management </a>
				</div>
			</nav>
		</header>
		<br>

		<div class="row">
			<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

			<div class="container">
				<h3 class="text-center">List of Users</h3>
				<label>Review Key: <%=r1.getKeyReview()%></label><br>
				<label>Pos Review: <%=r1.getPositiveReview()%></label><br>
				<hr>
				<br>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>Key Review</th>
							<th>Hotel Name</th>
							<th>Date</th>
							<th>Negative Review</th>
							<th>Positive Review</th>
							<th>Positive Review</th>
							<th>Reviewer Score</th>

						</tr>
					</thead>

					<tbody>
						<c:forEach var="review" items="${userReviews}">

							<tr>
								<td><c:out value="${review.Key_Review}" /></td>
								<td><c:out value="${review.Hotel_Name}" /></td>
								<td><c:out value="${review.Negative_Review}" /></td>
								<td><c:out value="${review.Positive_Review}" /></td>
								<td><c:out value="${review.Reviewer_Score}" /></td>
								<td><a href="delete?Key_Review=<c:out value='${review.Key_Review}' />">Delete</a></td>
							</tr>
						</c:forEach>
						<!-- } -->
					</tbody>

				</table>
			</div>
		</div>
	</form>
</body>

</html>