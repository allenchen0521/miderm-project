<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>admin_deleteMember</title>
</head>
<style>
* {
	font-family: 微軟正黑體;
}

html, body {
	width: 100%;
	height: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
	background-color: #eee;
}

.login {
	/* fancybox 最小寬度 */
 	min-width: 700px;
	background-color: #fff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
	padding: 20px;
	margin-bottom: 10px;
	font-weight: bold;
}

.registerBox a {
	text-decoration: none;
}

@media(max-width:750px) {
	.login { min-width: auto;}
}
</style>

<body>
	<div class="row">
		<div class="login col-sm-12">
			<h2>編輯會員資料</h2>
			<div>
				<fieldset>
					<div class="form-group">
						<label for="mem_id">會員編號:</label> <input type="text"
							class="form-control" id="mem_id" value="${member.mem_id}" disabled>
					</div>
					<div class="form-group">
						<label for="mem_username">會員帳號:</label> <input type="text"
							class="form-control" id="mem_username" name="mem_username" value="${member.mem_username}" disabled>
					</div>
					<div class="form-group">
						<label for="mem_name">會員名稱:</label> <input type="text"
							class="form-control" id="mem_name" name="mem_name" value="${member.mem_name}"
							placeholder="請輸入會員名稱" disabled> <span class="error">${errorMsg.errPassword}</span>
					</div>
					<div class="form-group">
						<label for="mem_level">會員等級:</label> <input type="text"
							class="form-control" id="mem_level" name="mem_level" value="${member.mem_level}"
							placeholder="請輸入會員等級" disabled> <span class="error">${errorMsg.errPassword}</span>
					</div>
				</fieldset>
				<button id="deleBtn" class="btn btn-info col-md-12">確定刪除</button>
			</div>
		</div>
	</div>
<script>
let requestUrl ="<c:url value='/' />";
$("#deleBtn").click(function(){
	$.ajax({
		url: "http://localhost:8080/" + requestUrl + "/admin/DeleteMemberProcessServlet",
		type: "post",
		data: {
			mem_id: $("#mem_id").val(),
		}
	});

	$.fancybox.close();
});
</script>
</body>
</html>