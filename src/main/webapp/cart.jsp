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
<%@ include file="jspf/footer.jspf" %>
	</body>
</html>