<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<!DOCTYPE html>
<html lang="kr">
<head>
<meta charset="UTF-8">
<title>총괄 페이지 로그인</title>
<link rel="stylesheet" href="<c:url value="/resources/css/adminLogin.css"/>"/>
</head>
<body>
	<form class="signUp" id="signupForm" action="/unyonyazal/admin" method="post">
	<div style ="width : 100%; min-height : 100px; text-align: center;">
	
	<img src="<c:url value="/resources/img/logo/logo_wide.png"/>" style ="max-height : 52px; width: 100%;" />
	
	<div style="height : 23px; margin-top : 15px; text-align: center; font-size: 17px;">
		<strong>커뮤 관리 페이지</strong>
		</div>
	</div>
		<br>
		 <label> <input type="text" name="id"
			class="signUpInput" placeholder="Type your username" autofocus
			required></label> 
			<label><input type="password" name="pw"
			class="signUpInput" placeholder="Choose a password" required></label>
		<br>
		<button type="submit" class="signUpButton">Login</button>
		<div id="msg" style="color: red;">${msg}</div>
	</form>

</body>
</html>