<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>扫码登录</title>
    <script src="${pageContext.request.contextPath}/assets/js/jquery-3.1.1.js"></script>

    <script>
        $(function () {
            var timerId = setInterval(checkLogin, 5000);
            function checkLogin() {
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/checkLoginState",
                    dataType: 'json',
                    data: {
                        uuid: $("#uuid").text()
                    },
                    success:function(result) {
                        console.log(result);
                        if (result.loginStatus == "login") {
                            clearInterval(timerId);
                            $("#caption").show();
                            window.location.href = "${pageContext.request.contextPath}/resume/editResume?userId=test";//跳到认证成功页面
                        }
                    }
                });
            }

        })
    </script>
</head>
<body>
    <div align="center">
        <span id="uuid" style="display: none">${uuid}</span>
        <img id="QRcode" src="${pageContext.request.contextPath}/assets/images/qrcode.png" style="width: 500px; height: 500px;"/>
        <p id="caption" style="display: none">扫码成功，正在登陆中。。。</p>
    </div>
</body>
</html>
