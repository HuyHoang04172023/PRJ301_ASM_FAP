/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.student;

import controller.authentication.authorization.BaseRBACController;
import dal.GradeDBContext;
import dal.StudentDBContext;
import entity.Account;
import entity.Grade;
import entity.Role;
import entity.Subject;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Hoang
 */
public class MarkReportController extends BaseRBACController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account,ArrayList<Role> roles)
            throws ServletException, IOException {
        String stuid = (String) request.getSession().getAttribute("userid");
        String semester = request.getParameter("semester");
        String subid = request.getParameter("subid");

        StudentDBContext stuDB = new StudentDBContext();
            ArrayList<String> semesters = stuDB.getSemesterByStudent(stuid);
        ArrayList<Subject> subs = stuDB.getCourseByStudentAndSemester(stuid, semester);

        GradeDBContext graDB = new GradeDBContext();
        ArrayList<Grade> grades = graDB.getGradeByStudentAndSubject(stuid, subid);
        ArrayList<Grade> grades_new = new ArrayList<>();
        for(Grade g : grades){
            if(!g.getExam().getAssessment().getCategory().equalsIgnoreCase("Final exam")&&
               !g.getExam().getAssessment().getCategory().equalsIgnoreCase("Final exam Resit")&&
               !g.getExam().getAssessment().getCategory().equalsIgnoreCase("Practical Exam")&&
               !g.getExam().getAssessment().getCategory().equalsIgnoreCase("Total")){
                grades_new.add(g);
                
            }
        }
        
        for(Grade g : grades){
            if(g.getExam().getAssessment().getCategory().equalsIgnoreCase("Practical Exam")){
                grades_new.add(g);
                
            }
        }
        
        for(Grade g : grades){
            if(g.getExam().getAssessment().getCategory().equalsIgnoreCase("Final exam")){
                grades_new.add(g);
                
            }
        }
        
        for(Grade g : grades){
            if(g.getExam().getAssessment().getCategory().equalsIgnoreCase("Final exam Resit")){
                grades_new.add(g);
                
            }
        }
        
        Grade total = new Grade();
        for(Grade g : grades){
            if(g.getExam().getAssessment().getCategory().equalsIgnoreCase("Total")){
                total = g;
                
            }
        }

        request.setAttribute("stuid", stuid);
        request.setAttribute("subs", subs);
        request.setAttribute("semesters", semesters);
        request.setAttribute("total", total);
        request.setAttribute("grades", grades_new);
        request.getRequestDispatcher("../view/student/MarkReport.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account,ArrayList<Role> roles)
            throws ServletException, IOException {

    }

}
