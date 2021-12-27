<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="lc" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>八卦微博</title>
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
<div class="leftPanel">
    <img src="images/caterpillar.jpg" alt="八卦微博"/>
</div>
<div>
    <h1>八卦 ... XD</h1>
    <ul>
        <li>谈天说地不奇怪</li>
        <li>分享讯息也可以</li>
        <li>随意谢谢表心情</li>
    </ul>
    <lc:Blahs/>
</div>
<a href="register.jsp">还不是会员？</a>
<div style="color: rgb(255, 0, 0); ">
    ${ requestScope.error }
</div>
<p></p>
<form id="loginPanel" method="post" action="login.do">
    <table style="background-color: #cccccc">
        <tr>
            <td colspan="2">会员登录</td>
        </tr>
        <tr>
            <td><label for="username">名称：</label></td>
            <td><input type="text" id="username" name="username" value="${ param.username }"></td>
        </tr>
        <tr>
            <td><label for="password">密码：</label></td>
            <td><input type="password" id="password" name="password"></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <input type="submit" value="登入">
            </td>
        </tr>
        <tr>
            <td colspan="2"><a href="forgot.html">忘记密码？</a></td>
        </tr>
    </table>
</form>
</body>
</html>