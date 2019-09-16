<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
 <meta charset="UTF-8">
 <title>login</title>
 <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css' />">
 <script src="<c:url value='/js/jquery-1.12.4.js' />"></script>
 <script src="<c:url value='/js/bootstrap.min.js' />"></script>
</head>
<style>
    * {
        font-family: 微軟正黑體;
    }

    html,
    body {
        width: 100%;
        height: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
        background-color: #eee;
    }

    .login {
        background-color: #fff;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
        padding: 20px;
        margin-bottom: 10px;
        font-weight: bold;
    }

    .registerBox {
        background-color: #fff;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
        padding: 10px;
        font-weight: bold;
    }

    .registerBox a {
        text-decoration: none;
    }
    
    .error { color: red}
</style>

<body>
    <div class="row">
        <div class="login col-sm-12">
            <h2>FunBar</h2>
            <form action="LoginServlet" method="post">
                <fieldset>
                    <div class="form-group">
                        <label for="username">帳號:</label>
                        <input type="text" class="form-control" id="username" name="mem_username" placeholder="請輸入電子信箱">
                        <span class="error">${errorMsg.errUserName}</span>
                    </div>
                    <div class="form-group">
                        <label for="password">密碼:</label>
                        <input type="password" class="form-control" id="password" name="mem_password" placeholder="請輸入密碼">
                        <span class="error">${errorMsg.errPassword}</span>
                    </div>
                </fieldset>
                <button type="submit" class="btn btn-info col-md-12">登入</button>
            </form>
        </div>

        <div class="registerBox col-sm-12">
            <span>沒有帳號嗎?</span><a href="<c:url value='/register/register.jsp' />">註冊</a>
        </div>
    </div>
</body>
</html>