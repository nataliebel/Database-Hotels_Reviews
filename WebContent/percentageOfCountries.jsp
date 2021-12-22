<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<%@ page import="java.util.*"%>
<%
Map<String, Float> countriesPercentageMap = (Map<String, Float>) session.getAttribute("getPercentageOfReviewersCountries");
if (countriesPercentageMap == null) {
	response.sendRedirect("welcome.jsp");
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Countries Percent</title>
</head>
<body>
	<div class="container">
		<div class="box">
			<h1>All the countries by percentage who visited in the hotel:</h1>
			<%
			for (Map.Entry<String, Float> entry : countriesPercentageMap.entrySet()) {
			%>
			<label><%=entry.getKey()%> - <%=entry.getValue()%>%</label><br/>
			<%
			}
			%>
		</div>
	</div>
</body>
</html>