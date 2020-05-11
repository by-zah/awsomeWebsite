<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="tagFile" tagdir="/WEB-INF/tags" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru" xmlns:fmt="http://www.w3.org/1999/XSL/Transform">
<%@ page isELIgnored="false" %>
	<head>
		<meta charset="utf-8">
		<title>Товар</title>
		<link rel="icon" href="img/icon.png" type="image/png">
		<link rel="stylesheet" type="text/css" href="css/style.css">
	</head>
	<body id="body">
	<%@ include file="jspf/header.jspf" %>

		<content class="content">
			<div class="content-container">
				<div class="main-product-container">
					<div class="main-image-container">
						<img class="product-image" src="img/image-showcase-0.png">
					</div>

					<div class="main-product-info-container">
						<h2 class="product-main-title">что-то там для примера</h2>
						<h4 class="product-category">аа категория продукт аа</h4>
						<h4 class="product-price">2 грн.</h4>
						<div class="small-divider"></div>
						<h4 class="size-title">размер</h4>
						<div class="size-container">
							<div class="product-size"><a href="#">M</a></div>
							<div class="product-size"><a href="#">L</a></div>
							<div class="product-size"><a href="#">XL</a></div>
						</div>
						<div class="measure-link"><a href="javascript:PopUpShow()">подбор размера</a></div>
						<button class="buy-button" id="buy-button" type="button" name="buy">добавить в корзину</button>
					</div>
				</div>
				<div class="product-description-container">
					<h3 class="description-title">описание</h3>
					<div class="small-divider" style="width: 13%; margin: 0 auto;"></div>
					<div class="product-description">
						лорем ипсум хддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддд дддддддддддддддддддддд
					</div>
				</div>
			</div>

		<div class="popup-wrapper-background" id="popupback">
			<div class="b-popup" id="popup">
			    <div class="b-popup-content">
			    	<div class="close-container"><a class="close" href="javascript:PopUpHide()">✖</a></div>
			    	<h2 class="popup-title">таблица мерок</h2>  
			    	<div class="popup-container">
			    		<div class="measurement-info">
			    			Мерки нужно мерять на своей одежде. Положите на пол свою футболку и померяйте замеры как нарисовано на картинке. Если вы все сделаете правильно, ошибиться с размером у вас не будет шанса.
			    			<div class="measurement-table">
				    		<table class="tg">
								<thead>
								  <tr>
								    <th class="tg-nrix"></th>
								    <th class="tg-baqh">xs</th>
								    <th class="tg-baqh">s</th>
								    <th class="tg-baqh">m</th>
								    <th class="tg-baqh">l</th>
								    <th class="tg-baqh">xl</th>
								    <th class="tg-baqh">xxl</th>
								  </tr>
								</thead>
								<tbody>
								  <tr>
								    <td class="tg-nrix">длина</td>
								    <td class="tg-baqh">67</td>
								    <td class="tg-baqh">67</td>
								    <td class="tg-baqh">69</td>
								    <td class="tg-baqh">71</td>
								    <td class="tg-baqh">72</td>
								    <td class="tg-baqh">72</td>
								  </tr>
								  <tr>
								    <td class="tg-nrix">рукав</td>
								    <td class="tg-baqh">17</td>
								    <td class="tg-baqh">18</td>
								    <td class="tg-baqh">18</td>
								    <td class="tg-baqh">18</td>
								    <td class="tg-baqh">20</td>
								    <td class="tg-baqh">20</td>
								  </tr>
								  <tr>
								    <td class="tg-nrix">грудь</td>
								    <td class="tg-baqh">46</td>
								    <td class="tg-baqh">48</td>
								    <td class="tg-baqh">50</td>
								    <td class="tg-baqh">52</td>
								    <td class="tg-baqh">54</td>
								    <td class="tg-baqh">56</td>
								  </tr>
								  <tr>
								    <td class="tg-nrix">плечи</td>
								    <td class="tg-baqh">40</td>
								    <td class="tg-baqh">41</td>
								    <td class="tg-baqh">43</td>
								    <td class="tg-baqh">44</td>
								    <td class="tg-baqh">46</td>
								    <td class="tg-baqh">48</td>
								  </tr>
								</tbody>
								</table>
					    	</div>
			    		</div>
			    		<div class="measurement-image">
			    			<img class="measurement-preview-image" src="img/clothes-measurement.jpeg">
			    		</div>
			    	</div>	    	
			    </div>
			</div>
		</div>

		</content>

	<%@ include file="jspf/footer.jspf" %>
		<script src="js/jQuery.min.js"></script>
		<script src="js/popup.js"></script>
	</body>
</html>