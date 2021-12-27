<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>八卦微博</title>
</head>
<body>
<c:if test="${ requestScope.errors != null }">
    <h1>新增会员失败</h1>
    <ul style="color: rgb(255, 0, 0);">
        <c:forEach var="error" items="${ requestScope.errors }">
            <li>${ error }</li>
        </c:forEach>
    </ul>
    <br>
</c:if>
<h1>会员注册</h1>
<form action="register.do" method="post">
    <table style="background-color: #cccccc">
        <tr>
            <td><label for="email">邮箱地址：</label></td>
            <td><input type="text" id="email" name="email"
                       size="25" maxlength="100" value="${ param.email }"></td>
        </tr>
        <tr>
            <td><label for="username">名称（最大16字符）：</label></td>
            <td><input type="text" id="username" name="username"
                       size="25" maxlength="16" value="${ param.username }"></td>
        </tr>
        <tr>
            <td><label for="password">密码（6到16字符）：</label></td>
            <td><input type="password" id="password" name="password" size="25" maxlength="16"></td>
        </tr>
        <tr>
            <td><label for="confirmedPassword">确认密码：</label></td>
            <td><input type="password" id="confirmedPassword" name="confirmedPasswd" size="25" maxlength="16"></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <input type="submit" value="注册">
            </td>
        </tr>
    </table>
</form>
</body>
</html>