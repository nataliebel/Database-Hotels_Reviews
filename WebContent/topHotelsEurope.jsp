<%@page import="connections.*"%>
<%@page import="databases.*"%>
<%@page import="objects.*"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%
ArrayList<Hotel> hotelsList = (ArrayList<Hotel>) session.getAttribute("getTopHotelsFromEurope");
if (hotelsList == null) {
	response.sendRedirect("welcome.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="box">
			<h1>Top hotels from all Europe</h1>
			<%
			for (Hotel h : hotelsList) {
			%>
			<h2><%=h.getHotelName()%></h2><br>
			<label><%=h.getHotelAddress()%></label><br>
			<label>Average Score: <%=h.getAvgScore()%></label><br>
			<%
			}
			%>
		</div>
	</div>
</body>
</html>