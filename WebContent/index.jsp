<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Join Us</title>
<link href="https://fonts.googleapis.com/css?family=ZCOOL+XiaoWei"
	rel="stylesheet">
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="container">
		<div class="box">
			<img class="avatar" src="img/user-avatar.png">
			<h1>Login Account</h1>
			<form action="LoginServlet" method="post">
				<p>UserName</p>
				<input type="text" placeholder="UserName" name="Name" required>
				<p>Password</p>
				<input type="password" placeholder="Password" name="Password"
					required> <input type="submit" value="Login"> 
					<br> <a href="registration.jsp">Create
					New Account</a>
			</form>
		</div>
	</div>
</body>
</html>