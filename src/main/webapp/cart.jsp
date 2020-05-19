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
	<c:choose>
		<c:when test="${cart.cart=='{}'}">
			<div class="empty-cart">
				<h2>
						${orderAlert}
				</h2>
				<img class="empty-cart-img" src="img/empty-cart.svg">
				<h4>в корзине нет товаров</h4>
				<a class="sending-button" href="http://localhost:8080/catalog.jsp?sortType=PRICE_UP">перейти в
					каталог</a>
			</div>
		</c:when>
		<c:otherwise>
		<div class="content-container cart-container">
			<h2 id="content-title">корзина</h2>
			<div class="mb-4 div-table">
				<div class="row">
					<div class="panel-body-catalog">
						<div class="left-sidebar">
							<h4>${orderAlert}</h4>
							<h5 id="alert">${alert}</h5>
							<div class="col-12">
								<div class="table-responsive">
									<table class="table table-striped">
										<thead>
										<tr class="first-tr">
											<th scope="col"></th>
											<th scope="col">Продукт</th>
											<th scope="col">Размер</th>
											<th class="text-center" scope="col">Количество</th>
											<th class="text-right" scope="col">Цена</th>
											<th></th>
										</tr>
										</thead>
										<tbody>
										<c:forEach items="${cart.getCart()}" var="entry">
											<tr id="${entry.key.productAttributes.get(0).id}">
												<td><img src="/${entry.key.productAttributes.get(0).photo}" style="width: 140px;"/></td>
												<td>${entry.key.title}</td>
												<c:choose>
													<c:when test="${entry.key.productAttributes.get(0).size=='null'}">
														<td>-</td>
													</c:when>
													<c:otherwise>
														<td>${entry.key.productAttributes.get(0).size}</td>
													</c:otherwise>
												</c:choose>
												<%--<td id="available${entry.key.productAttributes.get(0).id}"></td>--%>
												<td>
													<div o="popo" class="td-available-container">
														<button onclick="minus($(this))" class="input-available-button">
															-
														</button>
														<input class="form-control" available=""
															   id="priceOne${entry.key.productAttributes.get(0).id}"
															   productId="${entry.key.productAttributes.get(0).id}"
															   type="text"
															   value="${entry.value}" readonly="readonly"/>
														<button onclick="plus($(this))"
																class="input-available-button">+
														</button>
													</div>
												</td>
												<td class="text-right"
													id="price${entry.key.productAttributes.get(0).id}">${entry.key.productAttributes.get(0).price*entry.value} грн.</td>
												<td class="text-right">
													<button class="buy-button" onclick="deleteFromCart(this)"
															productId="${entry.key.productAttributes.get(0).id}">
														Удалить
													</button>
												</td>
											</tr>
										</c:forEach>
										<tr class="last-tr">
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td><strong>сумма к оплате</strong></td>
											<td class="text-right" id="cartSum"><strong>${cart.getSum()} грн.</strong></td>
										</tr>
										</tbody>
									</table>
								</div>
							</div>
							<div class="col mb-2">
								<div class="row">
									<div class="col-sm-12  col-md-6">
										<button class="buy-button" onclick="cleanCart()">отчистить корзину</button>
									</div>
									<div class="col-sm-12  col-md-6">
										<button class="buy-button"
												onclick="location.href='http://localhost:8080/catalog.jsp?sortType=PRICE_UP'">
											вернуться в каталог
										</button>
									</div>
									<div class="col-sm-12 col-md-6 text-right">
										<c:choose>
											<c:when test="${empty currentUser}">
												<button class="buy-button"
														onclick="location.href='http://localhost:8080/registration.jsp'">
													войти
												</button>
											</c:when>
											<c:otherwise>
												<button class="buy-button"
														onclick="location.href='http://localhost:8080/order.jsp'">
													Заказать
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
		</div>
		</c:otherwise>
	</c:choose>

</content>
<script>
	function plus(it) {
		let v = it.closest("div");
		let inp = v.find("input");
		let r = parseInt(inp.val());
		r = r + 1;
		$(inp).val(r);
		updateCart(inp);
	}

	function minus(it) {
		let v = it.closest("div");
		let inp = v.find("input");
		let r = parseInt(inp.val());
		r = r - 1;
		if (r < 1) {
			r = 1;
		}
		$(inp).val(r);
		updateCart(inp);
	}
</script>
<%@ include file="jspf/footer.jspf" %>
<script src="js/jQuery.min.js"></script>
<script src="/js/cart.js"></script>
</body>
</html>