<%-- 
    Document   : MarkReport
    Created on : Mar 11, 2024, 10:50:48 PM
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
                <td>TERM</td>
                <td>COURSE</td>
            </tr>
            <c:forEach items="${requestScope.semesters}" var="ses">
                <tr>
                    <td>${ses}</td>
                    <td>
                        <table>
                            <c:forEach items="${requestScope.subs}" var="sub">
                                <tr>
                                    <td>${sub.name}(${sub.id})</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
