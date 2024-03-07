<%-- 
    Document   : Attendance
    Created on : Mar 5, 2024, 11:08:58 PM
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
                            <input type="radio" 
                                   ${!att.isPresent?"checked=\"checked\"":""}
                                   name="isPresent${att.student.id}" value="no"/> No
                            <input type="radio" 
                                   ${att.isPresent?"checked=\"checked\"":""}
                                   name="isPresent${att.student.id}" value="yes"/> Yes
                        </td>
                        <td>
                            <input type="text" name="description${att.student.id}" value="${att.description}"/>
                        </td>
                        <td>${att.datetime}</td>
                    </tr>
                </c:forEach>
            </table>
            <input type="submit" value="Add"/>
        </form>

    </body>
</html>
