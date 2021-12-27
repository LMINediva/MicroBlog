<%@tag pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:choose>
    <c:when test="${requestScope.blahs == null}">
        <c:set var="blahs"
               value="${applicationScope.userService.newest}" scope="page"/>
    </c:when>
    <c:otherwise>
        <c:set var="blahs" value="${requestScope.blahs}" scope="page"/>
    </c:otherwise>
</c:choose>
<table style="text-align: left; width: 550px; height: 88px; border: 0;">
    <thead>
    <tr>
        <th>
            <hr>
        </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="blah" items="${pageScope.blahs}">
        <tr>
            <td style="vertical-align: top;">
                    ${blah.username}<br>
                <c:out value="${blah.txt}"/><br>
                <fmt:formatDate value="${blah.date}" type="both"
                                dateStyle="full" timeStyle="full"/>
                <c:if test="${sessionScope.login != null}">
                    <a href="delete.do?message=${blah.date.time}">删除</a>
                </c:if>
                <hr>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:remove var="blahs" scope="page"/>