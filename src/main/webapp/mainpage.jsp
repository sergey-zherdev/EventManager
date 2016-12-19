<%--
  Created by IntelliJ IDEA.
  User: Сергей
  Date: 15.12.2016
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form id="form1" action=${pageContext.request.contextPath}/view>
    <input type="SUBMIT" name="Query Items" value="View all events"/>
</form>
<form id="form2"action=${pageContext.request.contextPath}/create>
    <input type="SUBMIT" name="Query Items" value="Create new event"/>
</form>
</body>
</html>
