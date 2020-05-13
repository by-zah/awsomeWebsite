<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="tagFile" tagdir="/WEB-INF/tags" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru" xmlns:fmt="http://www.w3.org/1999/XSL/Transform">
<%@ page isELIgnored="false" %>
<head>
	<meta charset="utf-8">
	<title>Главная</title>
	<link rel="icon" href="img/icon.png" type="image/png">
	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<a class="back-to-top-button" id="back-to-top-button" href="#">↑</a>
<%@ include file="jspf/header.jspf" %>

<content class="content">
	<div class="content-container">
		<div class="content-banner" style="background-image: url('img/banner.jpg');" name="slide_show">
			<h1 class="content-title">awsome website merch store</h1>
			<a class="content-button" href="catalog.jsp?sortType=PRICE_UP">перейти к ассортименту</a>
		</div>
		<div class="content-divider"></div>
		<div class="content-showcase">

			<div class="showcase-card-viewer" style="background-image: url('img/image-showcase-0.png');">
				<h3 class="showcase-card-title">футболки</h3>
				<a class="showcase-card-button" href="catalog.jsp?sortType=PRICE_UP&category=Tshirt">перейти</a>
			</div>
			<div class="showcase-card-viewer" style="background-image: url('img/image-showcase-1.png');">
				<h3 class="showcase-card-title">фигурки</h3>
						<a class="showcase-card-button" href="catalog.jsp?sortType=PRICE_UP&category=Figure">перейти</a>
					</div>
					<div class="showcase-card-viewer" style="background-image: url('img/image-showcase-2.png');">
						<h3 class="showcase-card-title">игрушки</h3>
						<a class="showcase-card-button" href="catalog.jsp?sortType=PRICE_UP&category=Toy">перейти</a>
					</div>
					<div class="showcase-card-viewer" style="background-image: url('img/image-showcase-3.png');">
						<h3 class="showcase-card-title">аксессуары</h3>
						<a class="showcase-card-button" href="catalog.jsp?sortType=PRICE_UP&category=Accessory">перейти</a>
					</div>

				</div>
				<div class="content-divider"></div>
				<div class="content-card-holder">
					<h4 class="content-card-title">футболки</h4>
					<div class="content-card-holder-box" id="content-card-holder-box0"></div>
				</div>
				<div class="content-divider"></div>
				<div class="content-card-holder">
					<h4 class="content-card-title">фигурки</h4>
					<div class="content-card-holder-box" id="content-card-holder-box1"></div>
				</div>
				<div class="content-divider"></div>
				<div class="content-card-holder">
					<h4 class="content-card-title">игрушки</h4>
					<div class="content-card-holder-box" id="content-card-holder-box2"></div>
				</div>
				<div class="content-divider"></div>
				<div class="content-card-holder">
					<h4 class="content-card-title">аксессуары</h4>
					<div class="content-card-holder-box" id="content-card-holder-box3"></div>
				</div>
			</div>
		</content>

<%@ include file="jspf/footer.jspf" %>

		<script src="js/jQuery.min.js"></script>
		<script src="js/banner.js"></script>
		<script src="js/back-to-top.js"></script>
		<script src="js/ajax.js"></script>
	</body>
</html>