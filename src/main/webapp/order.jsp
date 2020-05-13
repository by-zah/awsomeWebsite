<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="tagFile" tagdir="/WEB-INF/tags" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru" xmlns:fmt="http://www.w3.org/1999/XSL/Transform">
<%@ page isELIgnored="false" %>
	<head>
		<meta charset="utf-8">
		<title>Заказ</title>
		<link rel="icon" href="img/icon.png" type="image/png">
		<link rel="stylesheet" type="text/css" href="css/style.css">
	</head>
	<body>
		<a class="back-to-top-button" id="back-to-top-button" href="#">↑</a>
		<%@ include file="jspf/header.jspf" %>

		<content class="content">
			<div class="content-container">
				<h2 id="content-title">ваш заказ</h2>
				<div class="orders-container" id="orders-container">

				</div>
				<div class="content-divider"></div>
				<div class="checkout-main-container">
					<form class="checkout-form" method="GET">
						<input type="text" class="checkout-form-input"></form>
					</form>
				</div>
			</div>
		</content>

		<%@ include file="jspf/footer.jspf" %>
		<script src="js/jQuery.min.js"></script>
		<script src="js/order.js"></script>
	</body>
</html>