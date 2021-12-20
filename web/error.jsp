<%@page import="java.util.List" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>会员新增失败</title>
</head>
<body>
<h1>新增会员失败</h1>
<ul style="color: rgb(255, 0, 0); ">
    <%
        List<String> errors = (List<String>) request.getAttribute("errors");
        for (String error : errors) {
    %>
    <li><%= error %>
    </li>
    <%
        }
    %>
</ul>
<a href="register.html">返回注册页面</a>
</body>
</html>