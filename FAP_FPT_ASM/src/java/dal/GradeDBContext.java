/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Assessment;
import entity.Exam;
import entity.Grade;
import entity.Student;
import entity.Subject;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hoang
 */
public class GradeDBContext extends DBContext<Grade> {

    public ArrayList<String> getSemesterByStudent(String stuid) {
        ArrayList<String> semesters = new ArrayList<>();
        try {
            String sql = "SELECT g.semester FROM Grade g\n"
                    + "INNER JOIN Student stu ON g.sid = stu.sid\n"
                    + "WHERE stu.sid = ? \n"
                    + "GROUP BY g.semester";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, stuid);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                semesters.add(rs.getString("semester"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return semesters;
    }

    public ArrayList<Subject> getCourseBYStudentAndSemester(String stuid, String semester) {
        ArrayList<Subject> subs = new ArrayList<>();
        try {
            String sql = "SELECT sub.subid,sub.subname FROM Grade g\n"
                    + "INNER JOIN Exam e ON g.eid = e.eid\n"
                    + "INNER JOIN Assessment a ON e.asid = a.asid\n"
                    + "INNER JOIN [Subject] sub ON a.subid = sub.subid\n"
                    + "INNER JOIN Student stu ON g.sid = stu.sid\n"
                    + "WHERE stu.sid = ? AND g.semester = ? \n"
                    + "GROUP BY sub.subid, g.semester,sub.subname";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, stuid);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Grade g = new Grade();
                Exam e = new Exam();
                Assessment a = new Assessment();
                Subject s = new Subject();
                
                s.setId(rs.getString("subid"));
                s.setName(rs.getString("subname"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subs;
    }

    public ArrayList<Grade> getGradeByStudentAndSubject(String stuid, int subid) {
        ArrayList<Grade> grades = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "g.gid,g.score,\n"
                    + "e.eid,\n"
                    + "a.asid,a.weight,a.name,\n"
                    + "s.sid,s.sname\n"
                    + "FROM Grade g \n"
                    + "INNER JOIN Exam e ON g.eid = e.eid\n"
                    + "INNER JOIN Assessment a ON e.asid = a.asid\n"
                    + "INNER JOIN Student s ON g.sid = s.sid\n"
                    + "WHERE s.sid = ? AND a.subid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, stuid);
            stm.setInt(2, subid);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Grade g = new Grade();
                Exam e = new Exam();
                Student s = new Student();
                Assessment a = new Assessment();

                g.setId(rs.getInt("gid"));
                g.setScore(rs.getFloat("score"));

                a.setId(rs.getInt("asid"));
                a.setWeight(rs.getInt("weight"));
                a.setName(rs.getString("name"));
                e.setId(rs.getInt("eid"));
                e.setAssessment(a);
                g.setExam(e);

                s.setId(rs.getString("sid"));
                s.setName(rs.getString("sname"));
                g.setStudent(s);

                grades.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return grades;
    }

    @Override
    public ArrayList<Grade> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Grade entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Grade entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Grade entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Grade get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
