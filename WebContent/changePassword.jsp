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
User user = (User) session.getAttribute("User");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<title>Change Your Password</title>
<link href="https://fonts.googleapis.com/css?family=ZCOOL+XiaoWei"
	rel="stylesheet">
<link href="css/styleChangePass.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="container">
		<div class="box">
			<h2>
				<%=user.getName()%>
			</h2>
			<p>Change your password:</p>
			<form action="ChangeUserPasswordServlet" method="post">
				<input type="hidden" name="Key_User" value="<%=user.getKeyUser()%>" />
				<label for="newPassword"></label> <input
					type="text" placeholder="Your new password" id="Password" name="Password" required><br>
				<input type="submit" name="submit" value="submit" />
				<a href="welcome.jsp" class="navbar-brand"> Return to Hotels Reviews Management </a>
			</form>
		</div>
	</div>
</body>
</html>