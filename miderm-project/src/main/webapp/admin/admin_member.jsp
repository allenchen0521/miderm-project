<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin_member</title>
<link rel="stylesheet" href="../js/fancybox/fancybox/jquery.fancybox-1.3.4.css">
</head>
<style>
body {
	min-height: 800px;
}
</style>
<body>
<div>
	<jsp:include page="../share/header.jsp" />
	<div class="jumbotron container">
		<h1 class="display-3">FarBar 管理者介面</h1>
		<hr class="my-4">
		<c:if test="${sessionScope.Member!=null}">
			<p class="username">${sessionScope.Member.mem_name},您好</p>
		</c:if>
	</div>

	<div class="col-sm-offset-9 col-sm-3">
		<label for="search">搜尋:</label> 
		<input type="text" class="form-control" id="search" placeholder="請輸入欲搜尋的會員帳號" >
	</div>

    <div class="main container table-responsive">
        <table class="table table-hover" id="demo"></table>
        <ul id="show" class="pagination"></ul>
    </div>
</div>
<script src="<c:url value='/js/jquery-1.8.3.js' />"></script>
<script src="../js/fancybox/fancybox/jquery.fancybox-1.3.4.js"></script>
<script src="../js/memberPage.js"></script>
<script>
$("#search").bind("input", function() {
	clearInterval(intervalId);

	let searchValue = $(this).val();
	let searchElement = $(".searchText");
	for(let i=0;i<searchElement.length;i++){
		if(searchElement.eq(i).html().indexOf(searchValue)<0) {
			console.log(searchElement.html().indexOf(searchValue));
			$("#demo tr").eq(i+1).hide();
		} else {
			console.log(searchElement.eq(i).html());
			$("#demo tr").eq(i+1).show();
		}
	}
});

$("#search").bind("blur", function() {
	let searchValue = $("#search").val();
	if(searchValue!="" && searchValue.length == 0) {
		intervalId = setInterval(function() {
			$.ajax({
			    url: "http://localhost:8080/" + requestUrl + "/admin/query_member.jsp",
			    method: "post",
			    dataType: "JSON",
			    success: function (res) {
			    	tododata = res;
			    	$("#demo").html("");
		            $("#show").html("");
		            init();
			    }
			});
		}, 3000);
	}
})
</script>
</body>
</html>