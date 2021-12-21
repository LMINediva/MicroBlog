<%@page pageEncoding="UTF-8" import="java.util.*, java.text.*, com.lc.model.*" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>八卦微博</title>
    <link rel="stylesheet" href="css/member.css">
</head>
<body>
<%
    List<Blah> blahs = (List<Blah>) request.getAttribute("blahs");
    if (blahs != null) {
%>
<div class="leftPanel">
    <img src="../images/caterpillar.jpg" alt="八卦微博"/>
    <br><br>
    ${ requestScope.username } 的微博
</div>
<table style="text-align: left; width: 510px; height: 88px; border: 0; ">
    <thead>
    <tr>
        <th>
            <hr>
        </th>
    </tr>
    </thead>
    <tbody>
    <%
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, Locale.CHINA);
        for (Blah blah : blahs) {
    %>
    <tr>
        <td style="vertical-align: top; ">
            <%= blah.getUsername() %> <br>
            <%= blah.getTxt() %> <br>
            <%= dateFormat.format(blah.getDate()) %>
            <hr>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<hr style="width: 100%; height: 1px; ">
<%
} else {
%>
<h1 style="color: rgb(255, 0, 0); ">
    ${ requestScope.username }用户不存在
</h1>
<%
    }
%>
</body>
</html>