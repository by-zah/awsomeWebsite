<!DOCTYPE html>
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
				<h2 id="content-title">каталог</h2>
				<div class="sort-buttons-container">
					<div class="catalog-menu-panel">
						<form class="catalog-menu-panel-form" method="GET" action="catalog">

							<div class="checkbox-full-container">

								<div class="checkbox-container">
									<input class="search-checkbox" type='checkbox' name='tshirt' value='Tshirt'><p>одежда</p>
							    </div>
							    <div class="checkbox-container">
							   		<input class="search-checkbox" type='checkbox' name='toy' value='Toy'><p>игрушки</p>
							    </div>
							    <div class="checkbox-container">
							   		<input class="search-checkbox" type='checkbox' name='figure' value='Figure'><p>фигурки</p>
								</div>
								<div class="checkbox-container">
							  		<input class="search-checkbox" type='checkbox' name='accessory' value='Accessory'><p>аксессуары</p>
								</div>

							</div>

							<div class="form-checkbox-attributes">

						   		<label for="custom-select" class="attribute-label">цвет:</label>
						   		<select id="color" class="custom-select" multiple="multiple">
						   			<option class="color-option" name="color" value="red">красный</option>
						   			<option class="color-option" name="color" value="black">черный</option>
						   			<option class="color-option" name="color" value="grey">серый</option>
						   		</select>
						   		<label for="custom-select" class="attribute-label">размер:</label>
						   		<select id="size" class="custom-select" multiple="multiple">
						   			<option class="size-option" name="size" value="m">m</option>
						   			<option class="size-option" name="size" value="l">l</option>
						   			<option class="size-option" name="size" value="xl">xl</option>
						   		</select>

						   		<button class="checkbox-attributes-button" name="button" type="button" id="submit-search-button" onclick="getAll()">поиск</button>
						   </div>
						   <div class="sort-buttons">

								<label for="custom-select" class="sort-label">сортировать по:</label>
								<select id="sortType" class="custom-select">
									<option 
									<c:if test="${userRequestParameter.sortType==1}">selected</c:if> value="PRICE_UP">по возрастанию</option>
								  	<option <c:if test="${userRequestParameter.sortType==2}">selected</c:if>
		                            value="PRICE_DOWN">по убыванию</option>
								  	<option <c:if test="${userRequestParameter.sortType==3}">selected</c:if>
		                            value="ALPHABET_UP">по алфавиту (а-я)</option>
		                            <option
		                            <c:if test="${userRequestParameter.sortType==4}">selected</c:if>
		                            value="ALPHABET_DOWN">по алфавиту (я-а)</option>
		                            </select>
								</select>

							</div>
						</form>
					</div>
					
				</div>

				<div class="content-divider" style="width: 100%;"></div>

				<div class="content-card-holder">
					<h4 class="content-card-title"></h4>
					<div class="content-card-holder-box" id="content-card-holder-box0"></div>
				</div>

				<div class="content-divider" style="width: 100%;"></div>

				<div class="pages-container">
					<div class="pages-holder">
						<div class="page"><a class="page-link" href="#">загрузить еще</a></div>
					</div>
				</div>
				
			</div>

		</content>

		<%@ include file="jspf/footer.jspf" %>
		<script src="js/jQuery.min.js"></script>
		<script src="js/banner.js"></script>
		<script src="js/back-to-top.js"></script>
		<script src="js/catalog.js"></script>
	</body>
</html>