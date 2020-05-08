<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="tagFile" tagdir="/WEB-INF/tags" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru" xmlns:fmt="http://www.w3.org/1999/XSL/Transform">
<%@ page isELIgnored="false" %>
<head>
	<meta charset="utf-8">
	<title>Вход</title>
	<link rel="icon" href="img/icon.png" type="image/png">
	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<a class="back-to-top-button" id="back-to-top-button" href="#">↑</a>
<%@ include file="jspf/header.jspf" %>
<content class="content">
	<div class="content-container">
		<div class="form-holder">
			<h3 class="form-title">вход</h3>
			<form method="POST" action="/logIn" class="register-form">
				<label for="email">email:</label>
				<input type="email" name="email" required>
				<label for="password">введите пароль:</label>
				<input type="password" name="password" required>
				<button type="submit" name="submit">войти</button>
				<h5>
					${alert}
				</h5>
				<div class="no-account">все еще нет аккаунта? <a class="link-to-register" href="registration.jsp">зарегистрироваться</a>
				</div>
			</form>
		</div>
	</div>

</content>

<%@ include file="jspf/footer.jspf" %>
		<script src="js/jQuery.min.js"></script>
		<script src="js/back-to-top.js"></script>
	</body>
</html>