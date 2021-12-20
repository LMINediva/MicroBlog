<%@page import="java.util.*, java.text.*, com.lc.model.Blah" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>八卦微博</title>
    <link rel="stylesheet" href="css/member.css">
</head>
<body>
<div class="leftPanel">
    <img src="images/caterpillar.jpg" alt="八卦微博"/>
    <br><br>
    <a href='logout.do?username="${ sessionScope.login }"'>注销 ${ sessionScope.login }
    </a>
</div>
<form method="post" action="message.do">
    <label for="blabla">分享新鲜事...</label><br>
    <%
        String blabla = request.getParameter("blabla");
        if (blabla == null) {
            blabla = "";
        } else {
    %>
    信息要在140字以内<br>
    <%
        }
    %>
    <textarea id="blabla" cols="60" rows="4" name="blabla">
        ${ requestScope.blabla }
    </textarea>
    <br>
    <button type="submit">发布</button>
</form>
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
        List<Blah> blahs = (List<Blah>) request.getAttribute("blahs");
        for (Blah blah : blahs) {
    %>
    <tr>
        <td style="vertical-align: top; ">
            <%= blah.getUsername() %> <br>
            <%= blah.getTxt() %> <br>
            <%= dateFormat.format(blah.getDate()) %>
            <a href="delete.do?message=<%= blah.getDate().getTime() %>">删除</a>
            <hr>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<hr style="width: 100%; height: 1px; ">
</body>
</html>