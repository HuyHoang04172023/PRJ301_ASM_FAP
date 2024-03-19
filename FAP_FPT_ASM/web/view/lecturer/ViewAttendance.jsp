<%-- 
    Document   : ViewAttendance
    Created on : Mar 18, 2024, 10:27:11 PM
    Author     : Hoang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Attendance</title>
        <style>
            form {
                width: 100%;
                margin: 0 auto;
                text-align: center; 
            }

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
            input[type=submit] {
                background-color: orange;
                border: none;
                color: white;
                padding: 10px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 10px auto;
                cursor: pointer;
                border-radius: 4px;
            }
            input[type=submit]:hover {
                background-color: #ff7f00;
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
        <form action="attendance" method="POST">
            <%int i = 1;%>
            <input type="hidden" name="sesid" value="${param.sesid}" />
            <table border="1px">
                <tr>
                    <td>NO</td>
                    <td>GROUP</td>
                    <td>CODE</td>
                    <td>NAME</td>
                    <td>GENDER</td>
                    <td>STATUS</td>
                    <td>COMMENT</td>
                    <td>TAKER</td>
                    <td>RECORD TIME</td>
                </tr>
                <c:forEach items="${requestScope.attends}" var="att">
                    <tr>
                        <td>
                            <%= i %>
                            <% i++; %>
                        </td>
                        <td>${att.session.group.name}</td>
                        <td>${att.student.id}</td>
                        <td>${att.student.name}</td>
                        <td>
                            <c:if test="${att.student.gender}">
                                <img src="../img/male.png" width="30px" height="30px" alt="male"/>
                            </c:if>
                            <c:if test="${!att.student.gender}">
                                <img src="../img/female.png" width="30px" height="30px" alt="female"/>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${att.isPresent}">
                                <p style="color: green">Present</p>
                            </c:if>
                                <c:if test="${!att.isPresent}">
                                <p style="color: red">Absent</p>
                            </c:if>
                        </td>
                        <td>
                            <p>${att.description}</p>
                        </td>
                        <td>${att.session.lecturer.id}</td>
                        <td>${att.datetime}</td>
                    </tr>
                </c:forEach>
            </table>
        </form>

    </body>
</html>

