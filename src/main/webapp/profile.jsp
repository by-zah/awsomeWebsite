<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="tagFile" tagdir="/WEB-INF/tags" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru" xmlns:fmt="http://www.w3.org/1999/XSL/Transform">
<%@ page isELIgnored="false" %>
<head>
    <meta charset="utf-8">
    <title>Профиль</title>
    <link rel="icon" href="img/icon.png" type="image/png">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<a class="back-to-top-button" id="back-to-top-button" href="#">↑</a>
<%@ include file="jspf/header.jspf" %>

<content class="content">
    <div class="content-container">
        <h2 id="content-title">ваш профиль</h2>
        <div class="profile-info-container">
            <div class="profile-default-icon"><img class="profile-image" src="img/user.png"></div>
            <div class="profile-account-info">
                <div class="account-text-info">
                    <h2 class="profile-info-title">информация об аккаунте</h2>
                    <h3 class="account-info-title" id="account-email">ваш email: <strong>sapazale@gmail.com</strong></h3>
                    <h3 class="account-info-title" id="account-phone">ваш телефон: <strong>+380984324783</strong></h3>
                </div>
            </div>
        </div>
        <div class="content-divider" style="width: 100%;"></div>
        <div class="profile-order-container">
            <h3 class="profile-order-main-title">ваши заказы</h3>
            <%--<h3 class="profile-empty-orders">здесь пока пусто :(</h3>
            <a class="buy-button" type="button" href="catalog.jsp?sortType=PRICE_UP" style="display: block; margin: 0 auto;">перейти в каталог</a>--%>
            <div class="profile-orders">
                <div class='order-wrapper'>
                    <div class='order'>
                        <div class="order-main-holder">
                            <div class='order-image-container'><img class='order-image' src='images/2_tshirt_deadpool.jpg'></div>
                            <div class='goods-description-container'>
                                <div class='order-product-title-container'>
                                    <h3 class='order-product-title'>DEADPOOL T-SHIRT FOR ADULTDS</h3>
                                </div>
                                <div class='order-product-description-container'>
                                    <div class='order-product-color-container'>
                                        <h3 class='order-color-title'>цвет:</h3>
                                        <div class='order-product-color' style='background-color: red'></div>
                                    </div>
                                    <h3 class='order-product-price'>цена: 850 грн.</h3>
                                    <div class='order-product-amount-container'>
                                        <h3 class='order-amount-title'>кол-во:</h3>
                                        <input class='order-product-amount' value='1' disabled>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="order-main-holder">
                            <div class='order-image-container'><img class='order-image' src='images/8_eredin.jpg'></div>
                            <div class='goods-description-container'>
                                <div class='order-product-title-container'>
                                    <h3 class='order-product-title'>ФИГУРКА FUNKO POP! EREDIN - THE WITCHER</h3>
                                </div>
                                <div class='order-product-description-container'>
                                    <h3 class='order-product-price'>цена: 400 грн.</h3>
                                    <div class='order-product-amount-container'>
                                        <h3 class='order-amount-title'>кол-во:</h3>
                                        <input class='order-product-amount' value='1' disabled>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <h3 class='order-product-price' style="margin: 10px 40px; text-align: right;">статус заказа: <span style="color: red;">в обработке</span></h3>
                    </div>
                </div>
            </div>
        </div>
    </div>
</content>

<%@ include file="jspf/footer.jspf" %>
</body>
</html>
