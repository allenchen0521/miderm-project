<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>register</title>
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

    .register {
        background-color: #fff;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
        padding: 20px;
        margin-bottom: 10px;
        font-weight: bold;
    }

    .loginBox {
        background-color: #fff;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
        padding: 10px;
        font-weight: bold;
    }

    .loginBox a {
        text-decoration: none;
    }

    .error { color: red}
</style>
<script>
let requestUrl = "<c:url value='/' />";
document.addEventListener("DOMContentLoaded", function(){
    document.getElementById("mem_username").addEventListener("blur", checkUserName, false);
    document.getElementById("mem_name").addEventListener("blur", checkName, false);
    document.getElementById("mem_password").addEventListener("blur", checkPwd, false);
    document.getElementById("doubleCheck").addEventListener("blur", doubleCheck), false;

    document.getElementById("mem_username").addEventListener("focus", clearError, false);
    document.getElementById("mem_name").addEventListener("focus", clearError, false);
    document.getElementById("mem_password").addEventListener("focus", clearError, false);
    document.getElementById("doubleCheck").addEventListener("focus", clearError), false;
});

function checkUserName() {
    let idName = document.getElementById("mem_username").value;
    let idNameLength = idName.length;
    let regex = /^.+@.+\..{2,3}$/;
    let flag1 = false;

    document.getElementById("usernamesp").innerHTML = "";
    if (idName == "") {
        document.getElementById("usernamesp").innerHTML = "<img src='" + requestUrl +"img/error.png'>不可空白";
    } else if (regex.test(idName)) {
        flag1 = true;
        if (flag1) document.getElementById("usernamesp").innerHTML = "<img src='" + requestUrl +"img/correct.png'>帳號格式正確";
    } else {
        document.getElementById("usernamesp").innerHTML = "<img src='"+ requestUrl + "/img/error.png'>帳號格式不正確";
    }
}

function checkName() {
    let name = document.getElementById("mem_name").value;
    let nameLength = name.length;

    document.getElementById("namesp").innerHTML = "";
    if(name == 0) document.getElementById("namesp").innerHTML = "<img src='"+ requestUrl + "/img/error.png'>姓名不可為空";
}

function checkPwd() {
    idPwd = document.getElementById("mem_password").value;
    idPwdLength = idPwd.length;
    let symbol = ['!', '@', '#', '$', '%', '^', '&', '*'];
    let flag1 = false, flag2 = false, flag3 = false;

    document.getElementById("pwdsp").innerHTML = "";
    if (idPwd == "") {
        document.getElementById("pwdsp").innerHTML = "<img src='" + requestUrl +"img/error.png'>密碼不可為空白";
    } else if (idPwdLength >= 6) {
        for (let i = 0; i < idPwdLength; i++) {
            let idPwdChar = idPwd.charAt(i).toUpperCase();
            if (idPwdChar >= 'A' && idPwdChar <= 'Z') {
                flag1 = true;
            } else if (idPwdChar >= 0 && idPwdChar <= 9) {
                flag2 = true;
            } else {
                // 特殊符號檢查
                for (let j = 0; j < symbol.length; j++) {
                    if (idPwdChar.indexOf(symbol[j]) != -1) {
                        flag3 = true;
                        break;
                    }
                }
            }
            if (flag1 && flag2 && flag3) break;
        }
        if (flag1 && flag2 && flag3) {
            document.getElementById("pwdsp").innerHTML = "<img src='" + requestUrl +"img/correct.png'>密碼格式正確";
        }
        else {
            document.getElementById("pwdsp").innerHTML = "<img src='" + requestUrl +"img/error.png'>密碼格式不正確";
        }
    } else {
        document.getElementById("pwdsp").innerHTML = "<img src='" + requestUrl +"img/error.png'>密碼至少6個字元";
    }
}

function doubleCheck() {
    original = document.getElementById("mem_password").value;
    doubleCheck = document.getElementById("doubleCheck").value;
    document.getElementById("doublesp").innerHTML

    if(doubleCheck.length == 0) {
        document.getElementById("doublesp").innerHTML = "<img src='" + requestUrl +"img/error.png'>驗證密碼不可為空白";
    } else if(original == doubleCheck) {
        document.getElementById("doublesp").innerHTML = "<img src='" + requestUrl +"img/correct.png'>驗證密碼正確";
    } else {
        document.getElementById("doublesp").innerHTML = "<img src='" + requestUrl +"img/error.png'>驗證密碼不正確";
    }
}

function clearError() {
    let error = document.querySelectorAll(".error");
    for(let i=0;i<error.length;i++) {
        error[i].innerHTML = "";
    }
}
</script>

<body>
    <div class="row">
        <div class="register col-sm-12">
            <h2>FunBar</h2>
            <p class="text-muted">註冊即可查看FunBar文章和活動</p>
            <form action="<c:url value='/register/Register' />" method="post">
                <fieldset>
                    <div class="form-group">
                        <label for="username">帳號:</label>
                        <input type="text" class="form-control" id="mem_username" name="mem_username" placeholder="請輸入電子信箱"
                        value="${param.mem_username}">
                        <span id="usernamesp"></span>
                        <span class="error">${errorMsg.errUserName}</span>
                    </div>
                    <div class="form-group">
                        <label for="username">姓名:</label>
                        <input type="text" class="form-control" id="mem_name" name="mem_name" placeholder="請輸入用戶名"
                        value="${param.mem_name}">
                        <span id="namesp"></span>
                        <span class="error">${errorMsg.errName}</span>
                    </div>
                    <div class="form-group">
                        <label for="password">密碼:</label>
                        <input type="password" class="form-control" id="mem_password" name="mem_password" placeholder="請輸入密碼">
                        <span id="pwdsp"></span>
                        <span class="error">${errorMsg.errPassword}</span>
                    </div>
                    <div class="form-group">
                        <label for="password">確認密碼:</label>
                        <input type="password" class="form-control" id="doubleCheck" name="doubleCheck" placeholder="請輸入重複輸入密碼">
                        <span id="doublesp"></span>
                        <span class="error">${errorMsg.errCheck}</span>
                    </div>
                </fieldset>
                <button type="submit" class="btn btn-info col-md-12">註冊</button>
            </form>
            <p class="text-muted">註冊即表示你同意我們的 <b>服務條款</b>、<b>資料政策</b>和<b>Cookie政策</b></p>
        </div>

        <div class="loginBox col-sm-12">
            <span>已經有帳號嗎?</span><a href="<c:url value='/login/login.jsp' />">登入</a>
        </div>
    </div>




</body>

</html>