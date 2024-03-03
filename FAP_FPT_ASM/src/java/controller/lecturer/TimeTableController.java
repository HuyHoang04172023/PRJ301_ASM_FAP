/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.lecturer;

import dal.SessionDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta .servlet.ServletException;
import jakarta .servlet.http.HttpServlet;
import jakarta .servlet.http.HttpServletRequest;
import jakarta .servlet.http.HttpServletResponse;
import java.util.Date;
import util.DateTimeHelper;

/**
 *
 * @author Hoang
 */
public class TimeTableController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String lid = request.getParameter("lid");
        String temp_from = request.getParameter("from");
        String temp_to = request.getParameter("to");
        
        java.sql.Date from = null;
        java.sql.Date to = null;
        Date today = new Date();
        
        if(temp_from == null){
            from = DateTimeHelper.utilDateToSqlDate(DateTimeHelper.getFirstDayOfWeek(today));
        }else{
            from = java.sql.Date.valueOf(temp_from);
        }
        
        if (temp_to == null) {
            to = DateTimeHelper.utilDateToSqlDate(DateTimeHelper.addDaysToDate(DateTimeHelper.sqlDateToUtilDate(from), 6));
        } else {
            to = java.sql.Date.valueOf(temp_to);
        }
        
        SessionDBContext sesDB = new SessionDBContext();
        sesDB.getSessionByLecturerIdFromTo(lid, from, to);
        
        request.getRequestDispatcher("../view/lecturer/TimeTable.jsp").forward(request, response);
        
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
