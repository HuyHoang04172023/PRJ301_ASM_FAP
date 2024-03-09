/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.student;

import dal.GradeDBContext;
import entity.Grade;
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
public class GradeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String stuid = request.getParameter("stuid");
        int subid = Integer.parseInt(request.getParameter("subid"));
        
        GradeDBContext gradeDB = new GradeDBContext();
        ArrayList<Grade> grades = gradeDB.getGradeByStudentAndSubject(stuid, subid);
        
        request.setAttribute("grades", grades);
        
        
        request.getRequestDispatcher("../view/student/Grade.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    }
}
