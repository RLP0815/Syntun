<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户</title>
</head>
<body>
<table border="1">
    <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>姓名</td>
    </tr>
        <c:choose>
            <c:when test="${not empty tAdmin}">
                <c:forEach items="${tAdmin}" var="admin" varStatus="vs">
                    <tr>
                        <td>${admin.id}</td>
                        <td>${admin.nickname}</td>
                        <td>${admin.passwd}</td>
                        <td>${admin.phoneno}</td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
               <tr>
                   <td colspan="2">无数据!</td>
               </tr>
            </c:otherwise>
        </c:choose>
</table>
</body>
</html>
