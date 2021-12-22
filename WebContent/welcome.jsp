<%@page import="connections.*"%>
<%@page import="databases.*"%>
<%@page import="objects.*"%>
<%@page import="java.sql.*"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
User user = (User) session.getAttribute("login");
if (user == null) {
	response.sendRedirect("index.jsp");
}
request.getSession().setAttribute("User",user);
// get countries list
CountriesDB countriesDB = new CountriesDB(ConnectionPro.getConnection());
ArrayList<Country> europeanCountriesList = (ArrayList<Country>) countriesDB.getEuropeanCountries();
ArrayList<Country> reviewersCountriesList = (ArrayList<Country>) countriesDB.getReviewersCountries();
ArrayList<Country> hotelsCountriesList = (ArrayList<Country>) countriesDB.getHotelsCountries();
// get hotels list
HotelsDB hotelsDB = new HotelsDB(ConnectionPro.getConnection());
ArrayList<Hotel> hotelsList = hotelsDB.getHotels();
// get compositions list
CompositionsDB compositionsDB = new CompositionsDB(ConnectionPro.getConnection());
ArrayList<Composition> compositionsList = compositionsDB.getAllComp();
%>
<!DOCTYPE html>
<html>
<head>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>welcome</title>
<link href="https://fonts.googleapis.com/css?family=ZCOOL+XiaoWei"
	rel="stylesheet">
<link href="css/styleWelcome.css" rel="stylesheet" type="text/css" />
</head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>query page</title>
<style>
.button {
	background-color: #23a5eb; /* blue */
	border: none;
	color: white;
	padding: 15px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
	-webkit-transition-duration: 0.4s; /* Safari */
	transition-duration: 0.4s;
	box-shadow: 0 12px 16px 0 rgba(0, 0, 0, 0.24), 0 17px 50px 0
		rgba(0, 0, 0, 0.19);
}

.h4 {
	font-family: verdana;
}
</style>
</head>
<body>
	<div class="container">
		<div class="box">
			<img class="avatar" src="img/user-avatar.png">
			<h1>Hotels Reviews</h1>
			<h2>
				Welcome,
				<%=user.getName()%>
			</h2>
			<a href="changePassword.jsp"><br>Change Your Password</a>
			<div style="width: 400px;" >
				<div style="float: left; width: 200px">
					<h4>ADD REVIEWS:</h4>
					<form action="AddReviewServlet" method="post">
					<input type="hidden" name="Key_User" value="<%=user.getKeyUser()%>" />
						<input type="submit" value="click here" />
					</form>
				</div>
				<div style="float: right; width: 200px">
					<h4>DELETE MY REVIEWS:</h4>
					<form action="DeleteAllUserReviewsServlet" method="post">
					<input type="hidden" name="Key_User" value="<%=user.getKeyUser()%>" />
						<input type="submit" value="click here"  />
					</form>
				</div>
			</div>
			<h4 class="h4">FIND INFO ABOUT HOTELS:</h4>
			<form action="TopHotelsEuropeServlet" method="post">
				<label>Top hotels from all Europe:</label><br> <input
					type="number" placeholder="Choose number of Top"
					name="topEuropeNum"> <input type="submit"
					name="topInEurope" value="lets go"><br>
			</form>
			<form action="TopHotelsCountryServlet" method="post">
				<label>Top hotels from: </label> <select name="Key_Country"
					id="Key_Country">
					<option value="-1">Select Country</option>
					<%
					for (Country c : hotelsCountriesList) {
					%>
					<option value=<%=Integer.toString(c.getKeyCountry())%>><%=c.getCountry()%></option>
					<%
					}
					%>
				</select><br> <input type="number" placeholder="Choose number of Top"
					name="topCountryNum"> <input type="submit"
					name="topInCountry" value="lets go"> <br>
			</form>
			<form action="ReviewersByPercentageServlet" method="post">
					<label>Reviewers by percentage who visited in:</label><br/>
					<select id="country1" name="country">
						<option>Select Country</option>
					</select>
					<select id="hotel1" name="Key_Hotel">
						<option>Select Hotel</option>
					</select>
				<div class="center">
					<input type="submit"
					name="findPercentage" value="lets go"> <br/>
				</div>
			</form>
			<form action="HotelWithMaxCompositionServlet" method="post">
				<label>Find the most popular hotel from: </label> <select name="Key_Country"
					id="Key_Country">
					<option value="-1">Select Country</option>
					<%
					for (Country c : hotelsCountriesList) {
					%>
					<option value=<%=Integer.toString(c.getKeyCountry())%>><%=c.getCountry()%></option>
					<%
					}
					%>
				</select><br>
				<label>To visit by: </label> <select name="Key_Composition"
					id="Key_Composition">
					<option value="-1">Select Composition</option>
					<%
					for (Composition c : compositionsList) {
					%>
					<option value=<%=Integer.toString(c.getKeyCompsition())%>><%=c.getComposition()%></option>
					<%
					}
					%>
				</select><br>
				<input type="submit"
					name="maxComposition" value="lets go">
			</form>
			<form action="ReviewsByHotelServlet" method="post">
				<h4 class="h4">FIND REVIEWS:</h4>
					<select id="country" name="country">
						<option>Select Country</option>
					</select>
					<select id="hotel" name="Key_Hotel">
						<option>Select Hotel</option>
					</select>
				<div class="center">
					<input type="submit"
					name="findReviews" value="lets go"> <br>
				</div>
			</form>			
		</div>
	</div>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
	<script	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$.ajax({
								url : "CountryHotelServlet",
								method : "GET",
								data : {operation : 'country'},
								success : function(data, textStatus,jqXHR) {
											console.log(data);
											let obj = $.parseJSON(data);
											$.each(obj, function(key, value) {
											$('#country').append('<option value="' + value.keyCountry + '">' + value.country + '</option>')});
											//$('select').formSelect();
										},
										error : function(jqXHR, textStatus, errorThrown) {
											$('#country').append('<option>Country Unavailable</option>');
										},
										cache : false
									});

									$('#country').change(function() {
											$('#hotel').find('option').remove();
											$('#hotel').append('<option>Select Hotel</option>');
											
											let cid = $('#country').val();
											let data = {
												operation : "hotel",
												id : cid
											};

											$.ajax({
												url : "CountryHotelServlet",
												method : "GET",
												data : data,
												success : function(data, textStatus,jqXHR) {
													console.log(data);
													let obj = $.parseJSON(data);
													$.each(obj,function(key,value) {
														$('#hotel').append('<option value="' + value.keyHotel + '">' + value.hotelName + '</option>')
													});
													//$('select').formSelect();
												},
												error : function(jqXHR, textStatus, errorThrown) {
													$('#hotel').append('<option>Hotels Unavailable</option>');
												},
												cache : false
												});
											});

						});
	</script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
	<script	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$.ajax({
								url : "CountryHotelServlet",
								method : "GET",
								data : {operation : 'country'},
								success : function(data, textStatus,jqXHR) {
											console.log(data);
											let obj = $.parseJSON(data);
											$.each(obj, function(key, value) {
											$('#country1').append('<option value="' + value.keyCountry + '">' + value.country + '</option>')});
											//$('select').formSelect();
										},
										error : function(jqXHR, textStatus, errorThrown) {
											$('#country1').append('<option>Country Unavailable</option>');
										},
										cache : false
									});

									$('#country1').change(function() {
											$('#hotel1').find('option').remove();
											$('#hotel1').append('<option>Select Hotel</option>');
											
											let cid = $('#country1').val();
											let data = {
												operation : "hotel",
												id : cid
											};

											$.ajax({
												url : "CountryHotelServlet",
												method : "GET",
												data : data,
												success : function(data, textStatus,jqXHR) {
													console.log(data);
													let obj = $.parseJSON(data);
													$.each(obj,function(key,value) {
														$('#hotel1').append('<option value="' + value.keyHotel + '">' + value.hotelName + '</option>')
													});
													//$('select').formSelect();
												},
												error : function(jqXHR, textStatus, errorThrown) {
													$('#hotel1').append('<option>Hotels Unavailable</option>');
												},
												cache : false
												});
											});

						});
	</script>
</body>
</html>