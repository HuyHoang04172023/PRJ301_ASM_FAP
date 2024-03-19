<%-- 
    Document   : TimeTable
    Created on : Mar 3, 2024, 10:06:51 PM
    Author     : Hoang
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Weekly Timetable</title>
        
        <style>
            form {
                width: 100%;
                margin: 0 auto;
                display: flex;
                justify-content: center;
                align-items: center;
                flex-direction: column;
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
                border: 1px solid ;
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
                margin: 10px 0; /* Căn giữa theo chiều dọc */
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
        <form action="timetable" method="GET">
            <div>
                <input type="submit" value="View"/>
            </div>
            <br/>

            <table border="1px">
                <tr>
                    <td>
                        From:<input type="date" name="from" value="${requestScope.from}"/>
                    </td>
                    <c:forEach items="${requestScope.dates}" var="date">
                        <td><fmt:formatDate pattern="EEEE" value="${date}" /></td>
                    </c:forEach>
                </tr>
                <tr>
                    <td>
                        To:<input type="date" name="to" value="${requestScope.to}"/>
                    </td>
                    <c:forEach items="${requestScope.dates}" var="date">
                        <td><fmt:formatDate pattern="d/M" value="${date}" /></td>
                    </c:forEach>
                </tr>

                <c:forEach items="${requestScope.slots}" var="slot">
                    <tr>
                        <td>${slot.name}</td>
                        <c:forEach items="${requestScope.dates}" var="date">
                            <td>
                                <c:forEach items="${requestScope.sessions}" var="ses">
                                    <c:if test="${ses.slot.id eq slot.id and ses.date eq date}">
                                        <div style="color: #3eabcc"; >
                                            ${ses.group.name}<br/>
                                            -${ses.group.subject.id}<br/>
                                            at ${ses.room.name}<br/>
                                        </div>


                                        <c:if test="${ses.isTaken}">(<span style="color: green;">Attended</span>)</c:if>
                                        <c:if test="${!ses.isTaken}">(<span style="color: red;">Not Yet</span>)</c:if>
                                            <br/>
                                            <a href="attendance?sesid=${ses.id}">
                                            <c:if test="${ses.isTaken}">Edit</c:if>
                                            <c:if test="${!ses.isTaken}">Take</c:if>
                                            </a>
                                    </c:if>
                                </c:forEach>
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>
        </form>

    </body>
</html>
