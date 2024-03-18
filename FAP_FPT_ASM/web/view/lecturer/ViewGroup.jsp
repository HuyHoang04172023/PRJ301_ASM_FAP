<%-- 
    Document   : ViewGroup
    Created on : Mar 18, 2024, 11:12:34 PM
    Author     : Hoang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Group</title>
        <style>
            /* CSS for ViewGroup page */
            body {
                font-family: Arial, sans-serif;
            }

            table {
                border-collapse: collapse;
                width: 100%;
            }

            table, th, td {
                border: 1px solid #ccc;
            }

            th, td {
                padding: 8px;
                text-align: left;
            }

            th {
                background-color: #f0f0f0;
                color: #333;
            }

            a {
                color: #007bff;
                text-decoration: none;
            }

            a:hover {
                text-decoration: underline;
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
        <table border="1px">
            <tr>
                <td>TEMP</td>
                <c:if test="${not empty requestScope.subjects}">
                    <td>COURSE</td>
                </c:if>

                <c:if test="${not empty requestScope.groups}">
                    <td>GROUP</td>
                </c:if>
            </tr>
            <tr>
                <td>
                    <table>
                        <c:forEach items="${requestScope.semesters}" var="ses">
                            <tr>
                                <td><a href="?semester=${ses}">${ses}</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
                <c:if test="${not empty requestScope.subjects}">
                    <td>
                        <table>
                            <c:forEach items="${requestScope.subjects}" var="sub">
                                <tr>
                                    <td><a href="?semester=${param.semester}&&subid=${sub.id}">${sub.name}</a></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </td>
                </c:if>
                <c:if test="${not empty requestScope.groups}">
                    <td>
                        <table>
                            <c:forEach items="${requestScope.groups}" var="g">
                                <tr>
                                    <td><a href="?semester=${param.semester}&&subid=${param.subid}&&gid=${g.id}">${g.name}</a></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </td>
                </c:if>
            </tr>
        </table>
        <c:if test="${not empty requestScope.students}">
            <%int i = 1;%>
            <table border="1px">
                <tr>
                    <td>NO</td>
                    <td>GENDER</td>
                    <td>EMAIL</td>
                    <td>CODE</td>
                    <td>NAME</td>
                </tr>
                <c:forEach items="${requestScope.students}" var="stu">
                    <tr>
                        <td>
                            <%= i %>
                            <% i++; %>
                        </td>
                        <td>
                            <c:if test="${stu.gender}">
                                <img src="../img/male.png" width="30px" height="30px" alt="male"/>
                            </c:if>
                            <c:if test="${!stu.gender}">
                                <img src="../img/female.png" width="30px" height="30px" alt="female"/>
                            </c:if>
                        </td>
                        <td>
                            ${stu.email}<br/>
                            <a href="">View Grade</a>
                        </td>
                        <td>${stu.id}</td>
                        <td>${stu.name}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>
