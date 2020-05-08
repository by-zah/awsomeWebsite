<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="tagFile" tagdir="/WEB-INF/tags" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru" xmlns:fmt="http://www.w3.org/1999/XSL/Transform">
<%@ page isELIgnored="false" %>
<head>
	<meta charset="utf-8">
	<title>Корзина</title>
	<link rel="icon" href="img/icon.png" type="image/png">
	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<%@ include file="jspf/header.jspf" %>

<content class="content">
	<div class="empty-cart">
		<h2 class="content-title">
			корзина
		</h2>
		<img class="empty-cart-img" src="img/empty-cart.svg">
		<h4>в корзине нет товаров</h4>
		<a class="sending-button" href="#">перейти в каталог</a>
	</div>
		</content>

		<footer class="footer">
			<div class="footer-container">
				<div class="catalog-box">
					<h5 class="footer-title">каталог</h5>
					<ul class="nav-links">
						<li class="nav-item"><a class="nav-link" href="#">главная</a></li>
						<li class="nav-item"><a class="nav-link" href="#">футболки</a></li>
						<li class="nav-item"><a class="nav-link" href="#">игрушки</a></li>
						<li class="nav-item"><a class="nav-link" href="#">фигурки</a></li>
						<li class="nav-item"><a class="nav-link" href="#">комиксы</a></li>
					</ul>
				</div>
				<div class="social-box">
					<h5 class="footer-title">социальные сети</h5>
					<div class="social-icons">
						<a class="footer-icon" href="#"><img class="footer-icon-img" src="img/facebook.png"></a>
						<a class="footer-icon" href="#"><img class="footer-icon-img" src="img/instagram.png"></a>
						<a class="footer-icon" href="#"><img class="footer-icon-img" src="img/telegram.png"></a>
						<a class="footer-icon" href="#"><img class="footer-icon-img" src="img/youtube.png"></a>
					</div>
				</div>
			</div>
			<div class="footer-separator"></div>
			<h5 class="footer-copyright">awsome website © 2020-2020</h5>
		</footer>
	</body>
</html>