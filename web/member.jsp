<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="lc" tagdir="/WEB-INF/tags" %>
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
    <c:if test="${ requestScope.blabla != null }">
        信息要在140字以内<br>
    </c:if>
    <textarea id="blabla" cols="60" rows="4" name="blabla">
        ${ requestScope.blabla }
    </textarea>
    <br>
    <button type="submit">发布</button>
</form>
<lc:Blahs/>
<hr style="width: 100%; height: 1px; ">
</body>
</html>