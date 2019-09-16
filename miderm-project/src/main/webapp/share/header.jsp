<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>FarBar</title>
	<link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css' />">
	<script src="<c:url value='/js/jquery-1.12.4.js' />"></script>
	<script src="<c:url value='/js/bootstrap.min.js' />"></script>
</head>
<style>
*{ font-family: 微軟正黑體}
</style>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">FarBar</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="<c:url value='/' />index.jsp">Home</a></li>
					<li><a href="#">熱門特賣</a></li>
					<li><a href="#">文章分享</a></li>
					<li><a href="<c:url value='/shoppingcart/ShoppingCartServlet' />">購物中心</a></li>
					<li><a href="<c:url value='/shoppingcart/showcart.jsp' />">我的購物車</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="<c:url value='/register/register.jsp' />">Sign Up</a></li>
					<c:if test="${sessionScope.Member.mem_level>=9}">
						<li><a href="#" class="dropdown-toggle" data-toggle="dropdown">管理者後台</a>
							<ul class="dropdown-menu">
								<li><a href="<c:url value='/admin/admin_member.jsp' />">會員管理</a></li>
							</ul>
						</li>
					</c:if>
					<c:if test="${sessionScope.Member==null}">
						<li><a href="<c:url value='/login/login.jsp' />">Login</a></li>
					</c:if>
					<c:if test="${sessionScope.Member!=null}">
						<li><a href="<c:url value='/login/LogoutServlet' />">Logout</a>
						</li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>
</body>