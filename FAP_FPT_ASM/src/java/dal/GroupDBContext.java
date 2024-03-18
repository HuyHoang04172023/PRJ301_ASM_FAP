/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Group;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hoang
 */
public class GroupDBContext extends DBContext<Group> {

    public ArrayList<Group> getGroupBySemesterAndSubject(String semester, String subid) {
        ArrayList<Group> groups = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "g.gid,g.gname\n"
                    + "FROM Semester_Subject_Group ssg\n"
                    + "INNER JOIN Subject sub ON ssg.subid = sub.subid\n"
                    + "INNER JOIN [Group] g ON ssg.gid = g.gid\n"
                    + "WHERE ssg.semester = ? AND ssg.subid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, semester);
            stm.setString(2, subid);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Group g = new Group();
                g.setId(rs.getInt("gid"));
                g.setName(rs.getString("gname"));
                groups.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return groups;
    }

    public ArrayList<String> getAllSemester() {
        ArrayList<String> semesters = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "semester\n"
                    + "FROM Semester_Subject_Group\n"
                    + "GROUP BY semester";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                semesters.add(rs.getString("semester"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return semesters;
    }

    @Override
    public ArrayList<Group> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Group entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Group entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Group entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Group get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
