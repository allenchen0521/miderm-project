<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../share/header.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>shoppingcart</title>
</head>
<style>
* {
	font-family: 微軟正黑體;
}

.jumbotron {
	position: relative;
	padding: 20px;
}

.jumbotron .username {
	position: absolute;
	right: 10px;
	bottom: 2.5px;
}

.prodlist {
	float: left;
	width: 18%;
	min-height: 360px;
	padding: 5px;
	text-align: center;
	margin: 0 auto;
	border: 1px dashed black;
	margin: 1%;
}

.prodlist .prodtitle {
	height: 25px;
}

.prodlist img {
	display: block;
	width: 50%;
	height: 200px;
	margin: 0 auto;
}

.btnMargin {margin-top: 10px;}

@media ( max-width : 960px) {
	.prodlist {
		width: 31%;
	}
}

@media ( max-width : 600px) {
	.prodlist {
		width: 48%;
	}
}
</style>
<body>
	<div class="jumbotron container">
		<h1 class="display-3">購物中心</h1>
		<hr class="my-4">
		<c:if test="${sessionScope.Member!=null}">
			<p class="username">${sessionScope.Member.mem_name},您好</p>
		</c:if>
	</div>
	<div class="container">
		<c:forEach var="product" items="${products}">
			<div class="prodlist">
				<p class="prodtitle">${product.prod_title}</p>
				<figure>
					<img src="<c:url value='/'/>${product.prod_image}" />
				</figure>
				<figcaption>
					<p>建議售價: $${product.prod_price}</p>
					<form class="form-group row" action="<c:url value='/shoppingcart/InsertCartItemServlet' />" method="post">
						<label for="selectCount" class="col-sm-6 col-xs-12">購買數量:</label>
						<div class="col-sm-6 col-xs-12">
							<select class="form-control" id="selectCount" name="count">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</select>
						</div>
						<input type="hidden" name="prod_id" value="${product.prod_id}">
						<button type="submit" class="btn btn-success btnMargin">加入購物車</button>
					</form>
				</figcaption>
				
			</div>
		</c:forEach>
	</div>
</body>
</html>