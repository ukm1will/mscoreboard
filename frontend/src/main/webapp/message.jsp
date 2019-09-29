<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>
<head>
    <title>Swansea Bay Golf Club</title>
</head>

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<body>

<table class="w3-table-all w3-hoverable">
    <c:forEach var="golfer" items="${stablefordGolfers}">
        <tr>
            <td>${golfer.getPts()}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
