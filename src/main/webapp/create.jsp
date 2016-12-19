<%--
  Created by IntelliJ IDEA.
  User: Сергей
  Date: 17.12.2016
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Choose event</h1>
<form action="create" method="POST">
    <table border="0">

        <tr>
            <td>Type</td>
            <td>
                <select name="possible-result">
                    <option value="REMINDER">Reminder</option>
                    <option value="ALARM">Alarm</option>
                </select>
            </td>
        </tr>

        <tr>
            <td><b>Date</b></td>
            <td><input type="date" name="Date"
                       size="70"/></td>
        </tr>

        <tr>
            <td><b>Time</b></td>
            <td><input type="time" name="Time"
                       size="65"/></td>
        </tr>

        <tr>
            <td><b>Description</b></td>
            <td><input type="text" name="Description"
                       size="65"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Write" name="B"/></td>
        </tr>

    </table>
</form>
</body>
</html>
