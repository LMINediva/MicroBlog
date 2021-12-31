<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>忘记密码？</title>
</head>
<body>
<c:if test="${requestScope.error != null}">
    <div style="color: rgb(255, 0, 0);">
            ${requestScope.error}
    </div>
</c:if>
<form action="password.do" method="post">
    <table>
        <tr>
            <td><label for="name">用户名称：</label></td>
            <td><input type="text" id="name" name="name" value="${param.name}"></td>
        </tr>
        <tr>
            <td><label for="email">用户邮件：</label></td>
            <td><input type="text" id="email" name="email" value="${param.email}"></td>
        </tr>
        <tr>
            <td><input type="submit" value="发送"></td>
        </tr>
    </table>
</form>
</body>
</html>