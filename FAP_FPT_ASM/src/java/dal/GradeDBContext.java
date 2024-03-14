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

    public ArrayList<Grade> getGradeByStudentAndSubject(String stuid, String subid) {
        ArrayList<Grade> grades = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "g.gid,g.score,\n"
                    + "e.eid,\n"
                    + "a.asid,a.weight,a.name,a.category,\n"
                    + "s.sid,s.sname\n"
                    + "FROM Grade g \n"
                    + "INNER JOIN Exam e ON g.eid = e.eid\n"
                    + "INNER JOIN Assessment a ON e.asid = a.asid\n"
                    + "INNER JOIN Student s ON g.sid = s.sid\n"
                    + "WHERE s.sid = ? AND a.subid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, stuid);
            stm.setString(2, subid);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Grade g = new Grade();
                Exam e = new Exam();
                Student s = new Student();
                Assessment a = new Assessment();

                g.setId(rs.getInt("gid"));
                g.setScore(rs.getFloat("score"));

                a.setId(rs.getString("asid"));
                a.setWeight(rs.getInt("weight"));
                a.setName(rs.getString("name"));
                a.setCategory(rs.getString("category"));
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
