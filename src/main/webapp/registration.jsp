<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="tagFile" tagdir="/WEB-INF/tags" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru" xmlns:fmt="http://www.w3.org/1999/XSL/Transform">
<%@ page isELIgnored="false" %>
<head>
	<meta charset="utf-8">
	<title>Регистрация</title>
	<link rel="icon" href="img/icon.png" type="image/png">
	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<a class="back-to-top-button" id="back-to-top-button" href="#">↑</a>
<%@ include file="jspf/header.jspf" %>
<content class="content">
	<div class="content-container">
		<div class="form-holder">
			<h3 class="form-title">регистрация</h3>
			<form method="POST" action="/registration" class="register-form">
				<label for="email">email:</label>
				<input type="email" name="email" required>
				<label type="tel">контактный номер:</label>
				<input type="tel" name="phone" value="+38" required>
				<label for="password">введите пароль:</label>
				<input type="password" name="password" required>
				<label for="password">подтверждение пароля:</label>
				<input type="password" name="passwordRep" required>
				<div class="checkbox-container">
					<label class="checkbox-label">
						<input type="checkbox" name="isMailingEnabled" value="true">
					</label>
					<div class="checkbox-description">я хочу получать уведомления <br> о скидках и новых товарах!</div>
				</div>
				<button type="submit" name="submit">зарегистрироваться</button>
				<h5>
					${alert}
             			</h5>
					</form>
				</div>
			</div>

		</content>

<%@ include file="jspf/footer.jspf" %>
		<script src="js/jQuery.min.js"></script>
		<script src="js/back-to-top.js"></script>
	</body>
</html>