<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>
<head>
    <title>Swansea Bay Golf Club</title>
</head>

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<body>
<form action="/view">
    <table class="w3-table-all w3-hoverable">
        <tr>
            <th>Date</th>
            <th>Competition</th>
            <th></th>
        </tr>
        <c:forEach var="url" items="${urls}">
            <tr>
                <td>${url.getDateOfCompetition()}</td>
                <td>${url.getCompetitionTitle()}</td>
                <td>
                    <form action="/view">
                        <input type="hidden" name="viewId" value="${url.getViewId()}">
                        <input type="submit" value="Gross Result">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>

