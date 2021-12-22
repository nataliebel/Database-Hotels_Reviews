<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<%@ page import="java.util.*"%>
<%@ page import="objects.*"%>
<%
Hotel hotel = (Hotel) session.getAttribute("getHotelWithMaxComposition");
if (hotel == null) {
	response.sendRedirect("welcome.jsp");
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Hotel With Max Composition</title>
</head>
<body>
	<div class="container">
		<div class="box">
			<h1>The Most Popular Hotel For You:</h1>
			<label><%=hotel.getHotelName()%> , <%=hotel.getHotelAddress()%></label><br/>
		</div>
	</div>
</body>
</html>