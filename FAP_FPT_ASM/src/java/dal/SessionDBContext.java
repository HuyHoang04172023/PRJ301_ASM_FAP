/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Group;
import entity.Lecturer;
import entity.Room;
import entity.Session;
import entity.Subject;
import entity.TimeSlot;
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
                    + "g.gid,g.gname,\n"
                    + "s.subid,s.subname,\n"
                    + "r.rid,r.rnumber,\n"
                    + "t.tid,t.tname,t.tbegin,t.tend,\n"
                    + "l.lid,l.lname\n"
                    + "FROM Session ses INNER JOIN [Group] g ON ses.gid = g.gid\n"
                    + "                 INNER JOIN Subject s ON g.subid = s.subid\n"
                    + "                 INNER JOIN Room r ON ses.rid = r.rid\n"
                    + "                 INNER JOIN TimeSlot t ON ses.tid = t.tid\n"
                    + "                 INNER JOIN Lecturer l ON ses.lid = l.lid\n"
                    + "WHERE ses.lid = '?' AND ses.date BETWEEN '?' AND '?'";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Session ses = new Session();
                Group g = new Group();
                Subject sub = new Subject();
                Room r = new Room();
                TimeSlot t = new TimeSlot();
                Lecturer l = new Lecturer();
                
                
                
                
                ses.setId(rs.getInt("sesid"));
                ses.setDate(rs.getDate("date"));
                ses.setIsTaken(rs.getBoolean("istaken"));
                
                g.setId(rs.getInt("gid"));
                g.setName(rs.getString("gname"));
                sub.setId(rs.getInt("subid"));
                sub.setName(rs.getString("subname"));
                g.setSubject(sub);
                ses.setGroup(g);
                
                r.setId(rs.getInt("rid"));
                r.setName(rs.getString("rnumber"));
                ses.setRoom(r);
                
                t.setId(rs.getInt("tid"));
                t.setName(rs.getString("tname"));
                t.setBegin(rs.getTimestamp("tbegin"));
                t.setEnd(rs.getTimestamp("tend"));
                ses.setSlot(t);
                
                l.setId(rs.getString("lid"));
                l.setName(rs.getString("lname"));
                ses.setLecturer(l);
                sessions.add(ses);
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
