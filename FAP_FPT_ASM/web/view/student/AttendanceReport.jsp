<%-- 
    Document   : AttendanceReport
    Created on : Mar 19, 2024, 9:13:55 AM
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
        <a href="/FAP_FPT_ASM/home/indexHomeStudent.html"><input type="button" value="Home"/></a>
        <form action="markreport" method="GET">
        </form>
        <table>
            <tr>
                <td>
                    <table border = "1px">
                        <tr>
                            <c:if test="${not empty requestScope.semesters}">
                                <td>SEMESTER</td>
                            </c:if>

                            <c:if test="${not empty requestScope.subs}">
                                <td>COURSE</td>
                            </c:if>

                        </tr>
                        <tr>
                            <c:if test="${not empty requestScope.semesters}">
                                <td>
                                    <table>
                                        <c:forEach items="${requestScope.semesters}" var="ses">
                                            <tr>
                                                <td>
                                                    <a href="?stuid=${requestScope.stuid}&semester=${ses}">${ses}</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </td>
                            </c:if>

                            <c:if test="${not empty requestScope.subs}">
                                <td>
                                    <table>
                                        <c:forEach items="${requestScope.subs}" var="s">
                                            <tr>
                                                <td>
                                                    <a href="?stuid=${requestScope.stuid}&semester=${param.semester}&subid=${s.id}">
                                                        ${s.name}(${s.id})
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </td>
                            </c:if>
                        </tr>
                    </table>
                </td>
                <c:if test="${not empty requestScope.atts}">
                    <td>
                        <%int i = 1;%>
                        <table border="1px">
                            <tr>
                                <td>NO</td>
                                <td>DATE</td>
                                <td>SLOT</td>
                                <td>ROOM</td>
                                <td>LECTURER</td>
                                <td>GROUP NAME</td>
                                <td>STATUS</td>
                                <td>COMMENT</td>
                            </tr>
                            <c:forEach items="${requestScope.atts}" var="a">
                                <tr>
                                <td><%= i %>
                            <% i++; %></td>
                                <td>${a.session.date}</td>
                                <td>${a.session.slot.name}</td>
                                <td>${a.session.room.name}</td>
                                <td>${a.session.lecturer.id}</td>
                                <td>${a.session.group.name}</td>
                                <td>
                                    <c:if test="${empty a.isPresent}">
                                        <span style="color: red">Not Yet</span>
                                    </c:if>
                                    <c:if test="${a.isPresent}">
                                        <span style="color: green">Present</span>
                                    </c:if>
                                        <c:if test="${!a.isPresent}">
                                        <span style="color: red">Absent</span>
                                    </c:if>
                                        
                                </td>
                                <td>${a.description}</td>
                            </tr>
                            </c:forEach>
                        </table>
                    </td>
                </c:if>



            </tr>
        </table>
    </body>
</html>
