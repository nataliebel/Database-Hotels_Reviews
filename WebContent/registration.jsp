
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="objects.Country"%>
<%@page import="databases.CountriesDB"%>
<%@page import="connections.ConnectionPro"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>log in</title>
<link href="https://fonts.googleapis.com/css?family=ZCOOL+XiaoWei"
	rel="stylesheet">
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="container">
		<div class="regbox box">
			<img class="avatar" src="img/collaboration.png">
			<h1>REGISTER TO HOTEL REVIEWS</h1>
			<form action="RegisterServlet" method="post">
				<p>UserName </p>
				<input type="text" placeholder="UserName" name="Name" required>
				<p>Password </p>
				<input type="password" placeholder="Password" name="Password"
					required>
				<p>Gender </p>
				<select name="Gender">
					<option value="-1">Select Gender</option>
					<option value="F">Female</option>
					<option value="M">Male</option>
				</select>
				<p>Country </p>
				<select name="Key_Country" id="Key_Country" required>
					<option value="-1">Select Country</option>
					<%
					CountriesDB countries = new CountriesDB(ConnectionPro.getConnection());
								ArrayList<Country> countriesList = countries.getAllCountries();
								for(Country c:countriesList){
					%>
					<option value=<%=Integer.toString(c.getKeyCountry())%>><%=c.getCountry()%></option>
					<% } %>
				</select>
				 <input type="submit" value="Register"> <a href="index.jsp"><br>Already Have Account?</a>
			</form>
		</div>
	</div>
</body>
</html>