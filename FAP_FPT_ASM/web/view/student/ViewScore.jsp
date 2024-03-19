<%-- 
    Document   : ViewScoreStudent
    Created on : Mar 19, 2024, 6:10:22 AM
    Author     : Hoang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Score Student</title>
        <style>
            /* CSS for ViewScoreStudent page */
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f5f5f5; /* optional background color */
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

            .font-red {
                color: red;
            }

            .font-green {
                color: green;
            }

            .font-orange {
                color: orange;
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
            
            input[type=submit] {
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
            input[type=submit]:hover {
                background-color: #ff7f00;
            }
        </style>
    </head>
    <body>
        <a href="/FAP_FPT_ASM/home/indexHomeStudent.html"><input type="button" value="Home"/></a>
            <%int i = 1;%>
        <br/>
        
        <c:if test="${not empty requestScope.grades}">
            <table border="1px">
                <tr>
                    <td>NO</td>
                    <td>SUBJECT CODE</td>
                    <td>SUBJECT NAME</td>
                    <td>SEMESTER</td>
                    <td>AVERAGE MARK</td>
                    <td>STATUS</td>
                </tr>
                <c:forEach items="${requestScope.grades}" var="g">
                    <tr>
                        <td><%= i %>
                            <% i++; %></td>
                        <td>
                                ${g.exam.assessment.subject.id}
                        </td>
                        <td>${g.exam.assessment.subject.name}</td>
                        <td>${g.exam.semester}</td>
                        <td>${g.score}</td>
                        <td>
                            <c:if test="${g.score <5}">
                                <font color="Red">Not Passed</font>
                            </c:if>
                            <c:if test="${g.score >=5}">
                                <font color="Green">Passed</font>
                            </c:if>
                            <c:if test="${g.score eq null}">
                                <font color="Green">Studying</font>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

    </body>
</html>
