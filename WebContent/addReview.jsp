<%@page import="connections.*"%>
<%@page import="databases.*"%>
<%@page import="objects.*"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
User user = (User) request.getSession().getAttribute("User");
TypeOfTripDB typeOf = new TypeOfTripDB(ConnectionPro.getConnection());
ArrayList<TypeOfTrip> typeOfList = typeOf.getAllTypes();

CompositionsDB comp = new CompositionsDB(ConnectionPro.getConnection());
ArrayList<Composition> compList = comp.getAllComp();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Review</title>
<link href="https://fonts.googleapis.com/css?family=ZCOOL+XiaoWei"
	rel="stylesheet">
<link href="css/styleWelcome.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="container">
		<div class="box">
	<h1>Add Review</h1>
	<h2>Welcome, <%=user.getName()%></h2>
	<form action="SaveReviewServlet" method="post">
		<input type="hidden" name="Key_User" value="<%=user.getKeyUser()%>" />
		<label>write review on: </label> <select id="country">
			<option>Select Country</option>
		</select> <select id="hotel" name="Key_Hotel">
			<option>Select Hotel</option>
		</select>
		<br> <label for="Date">date (format dd/mm/yyyy):</label> <input
			type="text" id="Date" name="Date" required><br> <br>
		<label for="negRev">write negative review:</label><br>
		<textarea id="Negative_Review" name="Negative_Review" rows="4"
			cols="50" placeholder="Negative_Review" required></textarea>
		<br> <br> <label for="Positive_Review">write
			positive review:</label><br>
		<textarea id="Positive_Review" name="Positive_Review" rows="4"
			cols="50" placeholder="Positive_Review" required></textarea>
		<br> <br> <label for="Reviewer_Score">score (number
			between 0-10):</label> <input type="text" id="Reviewer_Score"
			name="Reviewer_Score" required><br> <br> <br>
		<label> type of trip </label> <select name="Key_Trip" id="Key_Trip"
			required>
			<option value="-1">Select type of trip</option>
			<%
			for (TypeOfTrip t : typeOfList) {
			%>
			<option value=<%=t.getKeyTrip()%>><%=t.getTypeOfTrip()%></option>
			<%
			}
			%>
		</select><br> <br> <label> composition </label> <select
			name="Key_Composition" id="Key_Composition" required>
			<option value="-1">Select composition</option>
			<%
			for (Composition c : compList) {
			%>
			<option value=<%=c.getKeyCompsition()%>><%=c.getComposition()%></option>
			<%
			}
			%>
		</select><br> <br> <label for="Num_Of_Nights">enter number of
			nights:</label> <input min="0" id="Num_Of_Nights" name="Num_Of_Nights"
			required><br> <br> <input type="submit"
			name="submit" value="submit" />
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
</body>
</html>
