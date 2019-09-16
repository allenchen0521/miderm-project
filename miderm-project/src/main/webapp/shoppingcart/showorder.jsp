<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../share/header.jsp" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>show order</title>
</head>

<body>
	<div class="jumbotron container">
		<h1 class="display-3">顯示訂單明細</h1>
		<hr>
	</div>
	<div class="container">
		<div class="table-responsive">
			<table class="table">
				<thead>
					<tr>
						<td colspan="6">
							訂單編號: ${sessionScope.orders.order_id} 成交時間: <fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${sessionScope.orders.order_time}" /> 金額: ${sessionScope.orders.total}
						</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="orderItem" items="${sessionScope.orders.orderItemlist}">
					<tr>
						<td>
							<img src="<c:url value='${orderItem.product.prod_image}' />" width="50px" height="100px">
						</td>
						<td>${orderItem.product.prod_title}</td>
						<td>${orderItem.product.prod_price}元</td>
						<td>${orderItem.orderitem_count}件</td>
						<td>${orderItem.subtotal}元</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>

</html>