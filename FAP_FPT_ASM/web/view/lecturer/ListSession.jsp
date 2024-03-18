<%-- 
    Document   : ListSession
    Created on : Mar 18, 2024, 2:45:33 PM
    Author     : Hoang
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body {
                background-color: #fff;
                color: #000;
                font-family: Arial, sans-serif;
            }
            table {
                border-collapse: collapse;
                width: 100%;
            }
            table, th, td {
                border: 1px solid orange;
            }
            th, td {
                padding: 8px;
                text-align: left;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            tr:hover {
                background-color: #ddd;
            }
            a {
                text-decoration: none;
                color: purple;
            }
            input[type=button] {
                background-color: orange;
                border: none;
                color: white;
                padding: 10px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                cursor: pointer;
                border-radius: 4px;
            }
            input[type=button]:hover {
                background-color: #ff7f00;
            }
        </style>

    </head>
    <body>
        <a href="/FAP_FPT_ASM/home/indexHomeLecturer.html"><input type="button" value="Home"/></a>
        <br/>
        <%int i = 1;%>
        <table border="1px">
            <tr>
                <td>NO</td>
                <td>COURSE</td>
                <td>DATE</td>
                <td>SLOT</td>
                <td>GROUP NAME</td>
                <td>STATUS</td>
            </tr>
            <c:forEach items="${requestScope.sessions}" var="ses">
                <tr>
                    <td>
                        <%= i %>
                        <% i++; %>
                    </td>
                    <td>${ses.group.subject.name} (${ses.group.subject.id})</td>
                    <td>
                        <fmt:formatDate pattern="dd-MM-yyyy" value="${ses.date}" />
                    </td>
                    <td>${ses.slot.name} 

                        (<fmt:formatDate pattern="HH:mm" value="${ses.slot.begin}"/>-<fmt:formatDate pattern="HH:mm" value="${ses.slot.end}"/>)
                    </td>
                    <td>${ses.group.name}</td>
                    <td>
                        <a href="attendance?sesid=${ses.id}">
                            <c:if test="${ses.isTaken}">Edit</c:if>
                            <c:if test="${!ses.isTaken}">Take</c:if>
                            </a>
                        </td>
                    </tr>
            </c:forEach>
        </table>
    </body>
</html>
