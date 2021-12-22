<%@page import="connections.*"%>
<%@page import="databases.*"%>
<%@page import="objects.*"%>
<%@page import="java.sql.*"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>

<%
ArrayList<Review> reviewsList = (ArrayList<Review>) session.getAttribute("getReviewsByHotel");
if (reviewsList == null) {
	response.sendRedirect("welcome.jsp");
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Reviews By Hotel</title>
</head>
<body>
	<div class="container">
		<div class="box">
			<%
			// get one review for accessing to the hotel name
			Review r1 = reviewsList.get(0);
					%>
			<h1>All Reviews About Hotel: <%=r1.getHotelName()%></h1>
			<h2>Address: <%=r1.getHotelAddress()%></h2>

			<%
			for (Review r: reviewsList) {
			%>
			<h2>------------------------------------------------------------------------------------------------------------</h2>
			<label>Date: <%=r.getDate()%></label><br/>
			<label>Negative Review: <%=r.getNegativeReview()%></label><br/>
			<label>Positive Review: <%=r.getPositiveReview()%></label><br/>
			<label>Reviewer Score: <%=r.getReviewerScore()%></label><br/>
			<label>Type Of Trip: <%=r.getTrip()%></label><br/>
			<label>Composition: <%=r.getComposition()%></label><br/>
			<label>Number Of Nights: <%=r.getNumOfNights()%></label><br/>
			<%
			}
			%>	
		</div>
	</div>
</body>
</html>