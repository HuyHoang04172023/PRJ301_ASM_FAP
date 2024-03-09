<%-- 
    Document   : Grade
    Created on : Mar 9, 2024, 11:23:53 PM
    Author     : Hoang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border = "1px">
            <tr>
                <td>GRADE CATEGORY</td>
                <td>GRADE ITEM</td>
                <td>WEIGHT</td>
                <td>VALUE</td>
                <td>COMMENT</td>
            </tr>
            <c:forEach items="${requestScope.grades}" var="g">
                <tr>
                    <td>GRADE CATEGORY</td>
                    <td>${g.exam.assessment.name}</td>
                    <td>${g.exam.assessment.weight}</td>
                    <td>${g.score}</td>
                    <td><p></p></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
