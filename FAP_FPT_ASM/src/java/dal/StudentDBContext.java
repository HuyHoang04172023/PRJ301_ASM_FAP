/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Student;
import entity.Subject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hoang
 */
public class StudentDBContext extends DBContext<Student> {

    public ArrayList<Student> getStudentByGroup(int gid) {
        ArrayList<Student> students = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "s.sid,s.sname,s.semail,s.sgender\n"
                    + "FROM Student s\n"
                    + "INNER JOIN GroupStudent gs ON s.sid = gs.sid\n"
                    + "INNER JOIN [Group] g ON gs.gid = g.gid\n"
                    + "WHERE g.gid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, gid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student stu = new Student();
                stu.setId(rs.getString("sid"));
                stu.setName(rs.getString("sname"));
                stu.setEmail(rs.getString("semail"));
                stu.setGender(rs.getBoolean("sgender"));
                students.add(stu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }

    public ArrayList<String> getSemesterByStudent(String stuid) {
        ArrayList<String> semesters = new ArrayList<>();
        try {
            String sql = "SELECT semester FROM StudentCourse\n"
                    + "WHERE sid = ? \n"
                    + "GROUP BY semester\n"
                    + "ORDER BY \n"
                    + "    CASE \n"
                    + "        WHEN semester = 'Spring2023' THEN 1 \n"
                    + "        WHEN semester = 'Summer2023' THEN 2 \n"
                    + "        WHEN semester = 'Fall2023' THEN 3 \n"
                    + "        WHEN semester = 'Spring2024' THEN 4 \n"
                    + "    END;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, stuid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                semesters.add(rs.getString("semester"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return semesters;
    }

    public ArrayList<Subject> getCourseByStudentAndSemester(String stuid, String semester) {
        ArrayList<Subject> subs = new ArrayList<>();
        try {
            String sql = "SELECT sc.subid, sub.subname FROM StudentCourse sc\n"
                    + "INNER JOIN [Subject] sub ON sc.subid = sub.subid\n"
                    + "WHERE sc.sid = ? AND sc.semester = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, stuid);
            stm.setString(2, semester);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();

                s.setId(rs.getString("subid"));
                s.setName(rs.getString("subname"));
                subs.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subs;
    }

    @Override
    public ArrayList<Student> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Student entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Student entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Student entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Student get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
