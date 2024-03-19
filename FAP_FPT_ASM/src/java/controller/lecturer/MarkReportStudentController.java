/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.lecturer;

import controller.authentication.authorization.BaseRBACController;
import dal.GradeDBContext;
import dal.StudentDBContext;
import entity.Account;
import entity.Grade;
import entity.Role;
import entity.Subject;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta .servlet.ServletException;
import jakarta .servlet.http.HttpServlet;
import jakarta .servlet.http.HttpServletRequest;
import jakarta .servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author Hoang
 */
public class MarkReportStudentController extends BaseRBACController {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account,ArrayList<Role> roles)
            throws ServletException, IOException {
        String stuid = request.getParameter("stuid");
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
        request.getRequestDispatcher("../view/lecturer/MarkReportStudent.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account,ArrayList<Role> roles)
            throws ServletException, IOException {

    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
