<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="lc" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>八卦微博</title>
    <link rel="stylesheet" href="css/member.css">
</head>
<body>
<c:choose>
    <c:when test="${ requestScope.blahs != null }">
        <div class="leftPanel">
            <img src="../images/caterpillar.jpg" alt="八卦微博"/>
            <br><br>
                ${ requestScope.username } 的微博
        </div>
        <lc:Blahs/>
        <hr style="width: 100%; height: 1px; ">
    </c:when>
    <c:otherwise>
        <h1 style="color: rgb(255, 0, 0); ">
                ${ requestScope.username }用户不存在
        </h1>
    </c:otherwise>
</c:choose>
</body>
</html>