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
<script type="text/javascript">

    function disable(f) {
        switch (f.possible.value) {
            case 'REMINDER':
                f.endDate.disabled = 1;
                f.endTime.disabled = 1;
                f.Description.disabled = 0;
                f.repeatTime.disabled = 1;
                break;
            case 'ALARM':
                f.endDate.disabled = 0;
                f.endTime.disabled = 0;
                f.Description.disabled = 1;
                f.repeatTime.disabled = 0;
                break;
        }
    }
    function disableA(f) {
        if (mess.checked)
            f.address.disabled = 0;
        else
            f.address.disabled = 1;
    }

</script>
<body>
<h1>Choose event</h1>

<form action="create" method="POST">
    <table border="0">

        <tr>
            <td><b>Type</b></td>
            <td>
                <select name="possible" onchange="disable(this.form)">
                    <option value="REMINDER">Reminder</option>
                    <option value="ALARM">Alarm</option>
                </select>
            </td>
        </tr>

        <tr>

            <td><b>Start Date</b></td>
            <td><input type="Date" name="startDate"
                       size="70" required="1" min="new Date()"/></td>
        </tr>

        <tr>
            <td><b>Start Time</b></td>
            <td><input type="time" name="startTime"
                       size="65" required="1"/></td>
        </tr>

        <tr>
            <td><b>End Date</b></td>
            <td><input type="Date" name="endDate"
                       size="70" disabled="1" required="1"/></td>
        </tr>

        <tr>
            <td><b>End Time</b></td>
            <td><input type="time" name="endTime"
                       size="65" disabled="1" required="1"/></td>
        </tr>
        <tr>
            <td><b>Repeat every</b></td>
            <td><input type="text" name="repeatTime"
                       size="3" disabled="1" required="1"/><b>hours</b></td>
            </td>
        </tr>

        <tr>
            <td><b>Description</b></td>
            <td><input type="text" name="Description"
                       size="65" required="1"/></td>
        </tr>

        <tr>
            <td>
                <p>Выберите тип отправляемого сообщения</p>
                <p><input type="checkbox" name="Console" value="1"> Вывод в консоль</p>
            </td>
        </tr>
        <tr>
            <td>
                <p><input type="checkbox" name="Message" value="1" id="mess" onchange="disableA(this.form)"> Сообщение
                    на почту </p>
            <td><b>Email: </b><input type="text" name="address"
                                     size="15" disabled="1" required="1"/></td>
            </td>

        </tr>

        <tr>
            <td colspan="2"><input type="submit" value="Write" name="B"/></td>
        </tr>

    </table>


</form>
</body>
</html>
