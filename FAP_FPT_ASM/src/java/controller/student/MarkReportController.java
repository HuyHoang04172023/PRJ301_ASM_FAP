/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.student;

import dal.GradeDBContext;
import entity.Subject;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author Hoang
 */
public class MarkReportController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String stuid = request.getParameter("stuid");
        String semester = request.getParameter("semester");
        GradeDBContext graDB = new GradeDBContext();
        ArrayList<String> semesters = graDB.getSemesterByStudent(stuid);
        ArrayList<Subject> subs = graDB.getCourseBYStudentAndSemester(stuid, semester);

        request.setAttribute("subs", subs);
        request.setAttribute("semesters", semesters);
        request.getRequestDispatcher("../view/student/MarkReport.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
