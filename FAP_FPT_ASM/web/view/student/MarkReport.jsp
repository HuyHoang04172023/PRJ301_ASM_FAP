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
        <title>Mark Report</title>
        <style>
            /* CSS for MarkReport page */
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
            }

            form {
                margin-bottom: 20px;
            }

            table {
                border-collapse: collapse;
                width: 100%;
            }

            table, th, td {
                border: 1px solid;
            }

            th, td {
                padding: 8px;
                text-align: left;
            }

            a {
                color: #007bff;
                text-decoration: none;
            }

            a:hover {
                text-decoration: underline;
            }

            .grade-table {
                margin-top: 20px;
            }

            .grade-table th {
                background-color: #f0f0f0;
                color: #333;
            }

            .grade-table td {
                padding: 8px;
            }

            .grade-table td font {
                font-weight: bold;
            }

            .green-text {
                color: green;
            }

            .red-text {
                color: red;
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

                <c:if test="${not empty requestScope.grades}">
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
                                    <td>${g.exam.assessment.category}</td>
                                    <td>${g.exam.assessment.name}</td>
                                    <td>${g.exam.assessment.weight}</td>
                                    <td>
                                        <c:if test="${g.score ne 0}">
                                            ${g.score}
                                        </c:if>

                                    </td>
                                    <td><p></p></td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td rowspan="2">Course total</td>
                                <td>Average</td>
                                <td colspan="3">
                                    <c:if test="${requestScope.total.score ne null}">
                                        ${requestScope.total.score}
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td>Status</td>
                                <td colspan="3">
                                    <c:if test="${requestScope.total.score eq null}">
                                        <font color="Green">Studying</font>
                                    </c:if>
                                    <c:if test="${requestScope.total.score < 5}">
                                        <font color="Red">Not Passed</font>
                                    </c:if>
                                    <c:if test="${requestScope.total.score >= 5}">
                                        <font color="Green">Passed</font>
                                    </c:if>
                                </td>
                            </tr>
                        </table>
                    </td>
                </c:if>

            </tr>
        </table>
    </body>
</html>
