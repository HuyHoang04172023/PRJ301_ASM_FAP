/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.student;

import controller.authentication.authorization.BaseRBACController;
import dal.SessionDBContext;
import dal.StudentDBContext;
import entity.Account;
import entity.Attendance;
import entity.Role;
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
public class AttendanceReportController extends BaseRBACController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AttendanceReportController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AttendanceReportController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account,ArrayList<Role> roles)
            throws ServletException, IOException {
        String stuid = (String) request.getSession().getAttribute("userid");
        String semester = request.getParameter("semester");
        String subid = request.getParameter("subid");
        
        
        
        StudentDBContext stuDB = new StudentDBContext();
        ArrayList<String> semesters = stuDB.getSemesterByStudent(stuid);
        ArrayList<Subject> subs = stuDB.getCourseByStudentAndSemester(stuid, semester);
        SessionDBContext sesDB = new SessionDBContext();
        ArrayList<Attendance> atts = sesDB.getgetAttendencesByStudent( stuid, subid);
        
        
        
        request.setAttribute("stuid", stuid);
        request.setAttribute("semesters", semesters);
        request.setAttribute("subs", subs);
        request.setAttribute("atts", atts);
        request.getRequestDispatcher("../view/student/AttendanceReport.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account,ArrayList<Role> roles)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
