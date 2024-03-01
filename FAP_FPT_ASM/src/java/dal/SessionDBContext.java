/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Session;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hoang
 */
public class SessionDBContext extends DBContext<Session> {

    public ArrayList<Session> getSessionByLecturerIdFromTo(String lid, Date from, Date to) {
        ArrayList<Session> sessions = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "ses.sesid,ses.date,ses.istaken,\n"
                    + "g.gid,g.gname,g.startdate,g.tfirst,g.tsecond,\n"
                    + "g.dayfirst,g.daysecond,g.totalslot,\n"
                    + "s.subid,s.subname,s.credit,\n"
                    + "r.rid,r.rnumber,\n"
                    + "t.tid,t.tname,t.tbegin,t.tend,\n"
                    + "l.lid,l.lname,l.lage,l.lgender,l.lemail\n"
                    + "FROM Session ses INNER JOIN [Group] g ON ses.gid = g.gid\n"
                    + "				 INNER JOIN Subject s ON g.subid = s.subid\n"
                    + "				 INNER JOIN Room r ON ses.rid = r.rid\n"
                    + "				 INNER JOIN TimeSlot t ON ses.tid = t.tid\n"
                    + "				 INNER JOIN Lecturer l ON ses.lid = l.lid\n"
                    + "WHERE ses.lid = '?' AND ses.date BETWEEN '?' AND '?'";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {  
                
                Session s = new Session();
                
                
                
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sessions;
    }

    @Override
    public ArrayList<Session> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Session entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Session entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Session entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Session get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
