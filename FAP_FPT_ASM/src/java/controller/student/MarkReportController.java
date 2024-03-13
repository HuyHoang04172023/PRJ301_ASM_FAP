/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.student;

import dal.GradeDBContext;
import dal.StudentDBContext;
import entity.Grade;
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
        String subid = request.getParameter("subid");
        
        StudentDBContext stuDB = new StudentDBContext();
        ArrayList<String> semesters = stuDB.getSemesterByStudent(stuid);
        ArrayList<Subject> subs = stuDB.getCourseByStudentAndSemester(stuid, semester);
        
        GradeDBContext graDB = new GradeDBContext();
        ArrayList<Grade> grades = graDB.getGradeByStudentAndSubject(stuid, subid);

        request.setAttribute("stuid", stuid);
        request.setAttribute("subs", subs);
        request.setAttribute("semesters", semesters);
        request.setAttribute("grades", grades);
        request.getRequestDispatcher("../view/student/MarkReport.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
