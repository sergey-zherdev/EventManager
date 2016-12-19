<?xml version = "1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Сергей
  Date: 17.12.2016
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title></title>
</head>
<body>
<table>
    <c:forEach items="${map}" var="entry">
        <tr>
            <td>${entry.value}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
