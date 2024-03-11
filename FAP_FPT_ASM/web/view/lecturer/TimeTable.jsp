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
        <title>JSP Page</title>
        <script>
        </script>
        <link rel="stylesheet" href="../css/lecturer/TimeTableStyle.css"/>
    </head>
    <body>
        <form action="timetable" method="GET">

            Lecturer:<input type="text" name="lid" value="${requestScope.lid}"/>
            <input type="submit" value="View"/>
            <br/>

            <table border="1px">
                <tr>
                    <td>
                        From:<input type="date" name="from" value="${requestScope.from}"/>
                    </td>
                    <c:forEach items="${requestScope.dates}" var="date">
                        <td><fmt:formatDate pattern="E" value="${date}" /></td>
                    </c:forEach>
                </tr>
                <tr>
                    <td>
                        To:<input type="date" name="to" value="${requestScope.to}"/>
                    </td>
                    <c:forEach items="${requestScope.dates}" var="date">
                        <td class="header-timetable"><fmt:formatDate pattern="d/M" value="${date}" /></td>
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
                                            -${ses.group.subject.name}<br/>
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
