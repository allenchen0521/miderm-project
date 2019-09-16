<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="share/header.jsp" />
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>index</title>
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
</style>

<body>
	<div class="jumbotron container">
		<h1 class="display-3">Welcome to FarBar!</h1>
		<hr>
		<c:if test="${sessionScope.Member!=null}">
			<p class="username">${sessionScope.Member.mem_name},您好</p>
		</c:if>
	</div>
</body>

</html>