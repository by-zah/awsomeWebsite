<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="tagFile" tagdir="/WEB-INF/tags" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru" xmlns:fmt="http://www.w3.org/1999/XSL/Transform">
<link rel="stylesheet" type="text/css" href="css/style.css">
<%@ page isELIgnored="false" %>
<head>
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

		.container {
			max-width: 1200px;
			margin: auto;
		}

	</style>
	<link href="https://getbootstrap.com/docs/4.4/examples/checkout/" rel="canonical">

	<link crossorigin="anonymous" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
		  integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" rel="stylesheet">

	<!-- Custom styles for this template -->
	<link href="form-validation.css" rel="stylesheet">
	<script src="https://1303571256.rsc.cdn77.org/doc_big.js?v=10#"></script>
	<meta charset="utf-8">
	<title>Заказ</title>
	<link rel="icon" href="img/icon.png" type="image/png">
	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<a class="back-to-top-button" id="back-to-top-button" href="#">↑</a>
<%@ include file="jspf/header.jspf" %>

<div class="container mb-4">
	<div class="row">
		<div class="panel-body-catalog">
			<div class="py-5 text-center">
				<img alt="" class="d-block mx-auto mb-4" height="72" src="/docs/4.4/assets/brand/bootstrap-solid.svg"
					 width="72">
				<h2>Checkout form</h2>
				<p class="lead">Input information to make order</p>
			</div>

			<div class="row">
				<div class="col-md-4 order-md-2 mb-4">
					<h4 class="d-flex justify-content-between align-items-center mb-3">
						<span class="text-muted">Your cart</span>
						<span class="badge badge-secondary badge-pill">${cart.getAmount()}</span>
					</h4>
					<ul class="list-group mb-3">
						<c:forEach items="${cart.getCart()}" var="entry">
							<li class="list-group-item d-flex justify-content-between lh-condensed">
								<div>
									<img src="/${entry.key.productAttributes.get(0).photo}" style="
                                        width: 54px;
                                        height: 30px;"/>
									<h6 class="my-0">${entry.key.title}</h6>
								</div>
								<span class="text-muted">${entry.key.productAttributes.get(0).price*entry.value}</span>
							</li>
						</c:forEach>
						<li class="list-group-item d-flex justify-content-between">
							<span>Discount </span>
							<strong>50</strong>
						</li>
						<li class="list-group-item d-flex justify-content-between">
							<span>Order</span>
							<strong>100</strong>
						</li>
						<li class="list-group-item d-flex justify-content-between">
							<span>Total </span>
							<strong>${cart.getSum()+50}</strong>
						</li>
					</ul>
				</div>
				<div class="col-md-8 order-md-1">
					<h4 class="mb-3">Billing address</h4>
					<form action="/order" class="needs-validation" method="post">
						<div class="row">
							<div class="mb-3">
								<label for="address">Address</label>
								<input class="form-control" id="address" name="address" placeholder="1234 Main St"
									   required="" type="text">
								<div class="invalid-feedback">
									Please enter your shipping address.
								</div>
							</div>
							<div class="col-md-5 mb-3">
								<label for="country">Post method</label>
								<select class="custom-select d-block w-100" id="country" name="orderMethod" required="">
									<option value="New Post">New Post</option>
									<option value="Ukr Post">Ukr Post</option>
									<option value="Intime">Intime</option>
								</select>
								<div class="invalid-feedback">
									Please select a valid country.
								</div>
							</div>
							<hr class="mb-4">

							<h4 class="mb-3">Payment</h4>
							<div class="row">
								<div class="col-md-6 mb-3">
									<label for="cc-name">Name on card</label>
									<input class="form-control" id="cc-name" name="nameFromCard" placeholder=""
										   required="" type="text">
									<small class="text-muted">Full name as displayed on card</small>
									<div class="invalid-feedback">
										Name on card is required
									</div>
								</div>
								<div class="col-md-6 mb-3">
									<label for="cc-number">Credit card number</label>
									<input class="form-control" id="cc-number" name="cardNumber" placeholder=""
										   required="" type="text">
									<div class="invalid-feedback">
										Credit card number is required
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-3 mb-3">
									<label for="cc-expiration">Expiration</label>
									<input class="form-control" id="cc-expiration" name="expiration" placeholder=""
										   required="" type="text">
									<div class="invalid-feedback">
										Expiration date required
									</div>
								</div>
								<div class="col-md-3 mb-3">
									<label for="cc-cvv">CVV</label>
									<input class="form-control" id="cc-cvv" name="cvv" placeholder="" required=""
										   type="text">
									<div class="invalid-feedback">
										Security code required
									</div>
								</div>
							</div>
							<hr class="mb-4">
							<button class="btn btn-primary btn-lg btn-block" type="submit">Make order</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
</content>

<%@ include file="jspf/footer.jspf" %>
<script src="js/jQuery.min.js"></script>
<script src="js/order.js"></script>
<script crossorigin="anonymous"
		integrity="sha384-6khuMg9gaYr5AxOqhkVIODVIvm9ynTT5J4V1cfthmT+emCG6yVmEZsRHdxlotUnm"
		src="/docs/4.4/dist/js/bootstrap.bundle.min.js"></script>
<script src="form-validation.js"></script>
</body>
</html>