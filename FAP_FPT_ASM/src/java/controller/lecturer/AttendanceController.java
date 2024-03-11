/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.lecturer;

import controller.authentication.BaseRequiredAuthenticationController;
import dal.SessionDBContext;
import entity.Account;
import entity.Attendance;
import entity.Session;
import entity.Student;
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
public class AttendanceController extends BaseRequiredAuthenticationController {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        int sesid = Integer.parseInt(request.getParameter("sesid"));
        SessionDBContext sesDB = new SessionDBContext();
        ArrayList<Attendance> attends = sesDB.getAttendencesBySession(sesid);

        request.setAttribute("attends", attends);
        request.getRequestDispatcher("../view/lecturer/Attendance.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        int sesid = Integer.parseInt(request.getParameter("sesid"));
        SessionDBContext sesDB = new SessionDBContext();
        ArrayList<Attendance> atts_old = sesDB.getAttendencesBySession(sesid);
        ArrayList<Attendance> atts_new = new ArrayList<>();

        for (Attendance att_old : atts_old) {
            Attendance att_new = new Attendance();
            att_new.setId(att_old.getId());
            att_new.setSession(att_old.getSession());
            att_new.setStudent(att_old.getStudent());
            att_new.setIsPresent(request.getParameter("isPresent" + att_new.getStudent().getId()).equals("yes"));
            att_new.setDescription(request.getParameter("description" + att_new.getStudent().getId()));
            //
            if (att_old.isIsPresent() != att_new.isIsPresent()
                    || (att_old.getDescription() == null && att_new.getDescription() != null)
                    || (att_old.getDescription() != null && !att_old.getDescription().equals(att_new.getDescription()))) {
                atts_new.add(att_new);
            }
        }

        sesDB.takeAttendanceBySesionID(sesid, atts_new);
        response.sendRedirect("attendance?sesid=" + sesid);
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
