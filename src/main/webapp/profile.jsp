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
                    <h3 class="account-info-title" id="account-email">ваш email: <strong>${currentUser.email}</strong>
                    </h3>
                    <h3 class="account-info-title" id="account-phone">ваш телефон:
                        <strong>${currentUser.number}</strong></h3>
                    <a class="buy-button" href="/logOut">Выйти</a>
                </div>
            </div>
        </div>
        <c:choose>
            <c:when test="${orders=='[]'}">
                <div class="empty-cart">
                    <img class="empty-cart-img" src="img/empty-cart.svg">
                    <h4>У вас нет заказов</h4>
                    <a class="sending-button" href="http://localhost:8080/catalog.jsp?sortType=PRICE_UP">перейти в
                        каталог</a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="content-divider" style="width: 100%;"></div>
                <div class="profile-order-container">
                    <h3 class="profile-order-main-title">ваши заказы</h3>
                        <%--<h3 class="profile-empty-orders">здесь пока пусто :(</h3>
                        <a class="buy-button" type="button" href="catalog.jsp?sortType=PRICE_UP" style="display: block; margin: 0 auto;">перейти в каталог</a>--%>
                    <div class="profile-orders">
                        <c:forEach items="${orders}" var="order">
                            <div class='order-wrapper'>
                                <jsp:useBean id="dateValue" class="java.util.Date"/>
                                <jsp:setProperty name="dateValue" property="time" value="${order.datePlaced}"/>
                                <h3>Заказ за: <fmt:formatDate value="${dateValue}" pattern="MM/dd/yyyy HH:mm"/></h3>

                                <div class='order'>
                                    <c:forEach items="${order.productAndAmount}" var="entry">
                                        <div class="order-main-holder">
                                            <div class='order-image-container'><img class='order-image'
                                                                                    src='/${entry.key.productAttributes.get(0).photo}'>
                                            </div>
                                            <div class='goods-description-container'>
                                                <div class='order-product-title-container'>
                                                    <h3 class='order-product-title'>${entry.key.title}</h3>
                                                </div>
                                                <div class='order-product-description-container'>
                                                    <c:choose>
                                                        <c:when test="${entry.key.productAttributes.get(0).color=='null'}">
                                                            <div class='order-product-color-container'>
                                                                <h3 class='order-color-title'></h3>
                                                                <div class='order-product-color'></div>
                                                            </div>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <div class='order-product-color-container'>
                                                                <h3 class='order-color-title'>цвет:</h3>
                                                                <div class='order-product-color'
                                                                     style='background-color: ${entry.key.productAttributes.get(0).color}'></div>
                                                            </div>
                                                            <div class='order-product-color-container'>
                                                                <h3 class='order-color-title'>
                                                                    размер:${entry.key.productAttributes.get(0).size}</h3>
                                                                <div class='order-product-color'></div>
                                                            </div>
                                                        </c:otherwise>
                                                    </c:choose>

                                                    <h3 class='order-product-price'>
                                                        цена: ${entry.key.productAttributes.get(0).price*entry.value}
                                                        грн.</h3>
                                                    <div class='order-product-amount-container'>
                                                        <h3 class='order-amount-title'>кол-во:</h3>
                                                        <input class='order-product-amount' value='${entry.value}'
                                                               disabled>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                </div>


            </c:otherwise>
        </c:choose>
    </div>

</content>

<%@ include file="jspf/footer.jspf" %>
</body>
</html>