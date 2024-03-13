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
        <form action="markreport" method="GET">
            <input type="text" value="${requestScope.stuid}" name="stuid"/>
            <input type="submit" value="VIEW"/>
        </form>
        <table>
            <tr>
                <td>
                    <table border = "1px">
                        <tr>
                            <td>SEMESTER</td>
                            <td>COURSE</td>
                        </tr>
                        <tr>
                            <td>
                                <table>
                                    <c:forEach items="${requestScope.semesters}" var="ses">
                                        <tr>
                                            <td>
                                                <a href="?stuid=HE170292&semester=${ses}">${ses}</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </td>

                            <td>
                                <table>
                                    <c:forEach items="${requestScope.subs}" var="s">
                                        <tr>
                                            <td>
                                                <a href="?stuid=HE170292&semester=${param.semester}&subid=${s.id}">
                                                    ${s.name}(${s.id})
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>

                <td>
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
                </td>
            </tr>
        </table>
    </body>
</html>
