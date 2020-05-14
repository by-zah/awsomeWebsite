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

	<style>
		.bd-placeholder-img {
			font-size: 1.125rem;
			text-anchor: middle;
			-webkit-user-select: none;
			-moz-user-select: none;
			-ms-user-select: none;
			user-select: none;
		}

		@media (min-width: 768px) {
			.bd-placeholder-img-lg {
				font-size: 3.5rem;
			}
		}


	</style>
	<!-- Custom styles for this template -->
	<style>
		.container {
			max-width: 960px;
			margin: auto;
		}

	</style>
</head>
<body>
<%@ include file="jspf/header.jspf" %>

<content class="content">
	<c:choose>
		<c:when test="${cart.cart=='{}'}">
			<div class="empty-cart">
				<h2 class="content-title">
					корзина
				</h2>
				<img class="empty-cart-img" src="img/empty-cart.svg">
				<h4>в корзине нет товаров</h4>
				<a class="sending-button" href="http://localhost:8080/catalog.jsp?sortType=PRICE_UP">перейти в
					каталог</a>
			</div>
		</c:when>
		<c:otherwise>
			<div class="container mb-4">
				<div class="row">
					<div class="panel-body-catalog">
						<div class="left-sidebar">
							<h4>${orderAlert}</h4>
							<h5 id="alert">${alert}</h5>
							<div class="col-12">
								<div class="table-responsive">
									<table class="table table-striped">
										<thead>
										<tr>
											<th scope="col"></th>
											<th scope="col">Продукт</th>
											<th scope="col">Размер</th>
											<th scope="col">Сколько доступно</th>
											<th class="text-center" scope="col">Количество</th>
											<th class="text-right" scope="col">Цена</th>
											<th></th>
										</tr>
										</thead>
										<tbody>
										<c:forEach items="${cart.getCart()}" var="entry">
											<tr id="${entry.key.productAttributes.get(0).id}">
												<td><img src="/${entry.key.productAttributes.get(0).photo}" style="
                                        width: 140px;
                                        height: 105px;"/></td>
												<td>${entry.key.title}</td>
												<c:choose>
													<c:when test="${entry.key.productAttributes.get(0).size=='null'}">
														<td>-</td>
													</c:when>
													<c:otherwise>
														<td>${entry.key.productAttributes.get(0).size}</td>
													</c:otherwise>
												</c:choose>
												<td id="available${entry.key.productAttributes.get(0).id}"></td>
												<td><input class="form-control" available=""
														   id="priceOne${entry.key.productAttributes.get(0).id}"
														   productId="${entry.key.productAttributes.get(0).id}"
														   type="text"
														   value="${entry.value}"/></td>
												<td class="text-right"
													id="price${entry.key.productAttributes.get(0).id}">${entry.key.productAttributes.get(0).price*entry.value}</td>
												<td class="text-right">
													<button class="buy-button" onclick="deleteFromCart(this)"
															productId="${entry.key.productAttributes.get(0).id}">
														Удалить
													</button>
												</td>
											</tr>
										</c:forEach>
										<tr>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td><strong>Total</strong></td>
											<td class="text-right" id="cartSum"><strong>${cart.getSum()}</strong></td>
										</tr>
										</tbody>
									</table>
								</div>
								<td class="text-right">
									<button class="buy-button" onclick="cleanCart()">Clean All</button>
								</td>
							</div>
							<div class="col mb-2">
								<div class="row">
									<div class="col-sm-12  col-md-6">
										<button class="buy-button"
												onclick="location.href='http://localhost:8080/catalog.jsp?sortType=PRICE_UP'">
											Continue Shopping
										</button>
									</div>
									<div class="col-sm-12 col-md-6 text-right">
										<c:choose>
											<c:when test="${empty currentUser}">
												<button class="buy-button"
														onclick="location.href='http://localhost:8080/registration.jsp'">
													SignUp
												</button>
											</c:when>
											<c:otherwise>
												<button class="buy-button"
														onclick="location.href='http://localhost:8080/order.jsp'">
													Checkout
												</button>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:otherwise>
	</c:choose>

</content>
<%@ include file="jspf/footer.jspf" %>
<script src="js/jQuery.min.js"></script>
<script src="/js/cart.js"></script>
</body>
</html>