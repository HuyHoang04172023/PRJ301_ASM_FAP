/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Attendance;
import entity.Group;
import entity.Lecturer;
import entity.Room;
import entity.Session;
import entity.Student;
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
    
    public ArrayList<Session> getSessionByLecturerID(String lid) {
        ArrayList<Session> sessions = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "s.sesid,s.istaken,s.[date],\n"
                    + "g.gid,g.gname,\n"
                    + "sub.subid,sub.subname,\n"
                    + "t.tname,tbegin,tend\n"
                    + "FROM Session s\n"
                    + "INNER JOIN [Group] g ON s.gid = g.gid\n"
                    + "INNER JOIN [Subject] sub ON g.subid = sub.subid\n"
                    + "INNER JOIN TimeSlot t ON s.tid = t.tid\n"
                    + "WHERE s.lid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lid);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                Session s = new Session();
                Group g = new Group();
                Subject sub = new Subject();
                TimeSlot t = new TimeSlot();
                
                s.setId(rs.getInt("sesid"));
                s.setIsTaken(rs.getBoolean("istaken"));
                s.setDate(rs.getDate("date"));

                sub.setId(rs.getString("subid"));
                sub.setName(rs.getString("subname"));
                g.setSubject(sub);
                g.setId(rs.getInt("gid"));
                g.setName(rs.getString("gname"));
                s.setGroup(g);

                t.setName(rs.getString("tname"));
                t.setBegin(rs.getTimestamp("tbegin"));
                t.setEnd(rs.getTimestamp("tend"));
                s.setSlot(t);
                sessions.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sessions;
    }
    
    public void takeAttendanceBySesionID(int sesid, ArrayList<Attendance> atts) {
        try {
            connection.setAutoCommit(false);
            String sql_get_isTaken = "SELECT istaken FROM Session WHERE sesid = ?";
            PreparedStatement stm_get_isTaken = connection.prepareStatement(sql_get_isTaken);
            stm_get_isTaken.setInt(1, sesid);
            ResultSet rs_get_isTaken = stm_get_isTaken.executeQuery();
            boolean isTaken = false;
            while (rs_get_isTaken.next()) {
                isTaken = rs_get_isTaken.getBoolean("istaken");
            }
            
            if (!isTaken) {
                for (Attendance a : atts) {
                    String sql_insert_att = "INSERT INTO [dbo].[Attendance]\n"
                            + "([sesid],[sid],[ispresent],[description],[datetime])\n"
                            + "VALUES\n"
                            + "(?,?,?,?,GETDATE())";
                    PreparedStatement stm_insert_att = connection.prepareStatement(sql_insert_att);
                    stm_insert_att.setInt(1, sesid);
                    stm_insert_att.setString(2, a.getStudent().getId());
                    stm_insert_att.setBoolean(3, a.isIsPresent());
                    stm_insert_att.setString(4, a.getDescription());
                    stm_insert_att.executeUpdate();
                }
            } else {
                for (Attendance a : atts) {
                    String sql_update_att = "UPDATE [dbo].[Attendance]\n"
                            + "   SET [ispresent] = ?\n"
                            + "      ,[description] = ?\n"
                            + "      ,[datetime] = GETDATE()\n"
                            + " WHERE sid = ? AND sesid = ?";
                    PreparedStatement stm_update_att = connection.prepareStatement(sql_update_att);
                    stm_update_att.setBoolean(1, a.isIsPresent());
                    stm_update_att.setString(2, a.getDescription());
                    stm_update_att.setString(3, a.getStudent().getId());
                    stm_update_att.setInt(4, sesid);
                    stm_update_att.executeUpdate();
                }
                
            }
            String sql_update_istaken_session = "UPDATE [dbo].[Session]\n"
                    + "SET [istaken] = 1\n"
                    + "WHERE sesid = ? ";
            PreparedStatement stm_update_istaken_session = connection.prepareStatement(sql_update_istaken_session);
            stm_update_istaken_session.setInt(1, sesid);
            stm_update_istaken_session.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public ArrayList<Attendance> getAttendencesBySession(int sesid) {
        ArrayList<Attendance> attends = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "s.sid, s.sname, s.sgender,\n"
                    + "g.gid,g.gname,\n"
                    + "a.aid,a.ispresent,a.description,a.datetime,\n"
                    + "l.lid,l.lname\n"
                    + "                    \n"
                    + "FROM Student s\n"
                    + "INNER JOIN GroupStudent grs ON s.sid = grs.sid\n"
                    + "INNER JOIN [Group] g ON grs.gid = g.gid\n"
                    + "INNER JOIN [Session] ses ON g.gid = ses.gid\n"
                    + "INNER JOIN Lecturer l ON ses.lid = l.lid\n"
                    + "LEFT JOIN Attendance a ON s.sid = a.sid AND ses.sesid = a.sesid\n"
                    + "WHERE ses.sesid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sesid);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                Attendance a = new Attendance();
                Session ses = new Session();
                Student stu = new Student();
                Group g = new Group();
                Lecturer l = new Lecturer();
                
                l.setId(rs.getString("lid"));
                l.setName(rs.getString("lname"));
                
                g.setId(rs.getInt("gid"));
                g.setName(rs.getString("gname"));
                
                ses.setLecturer(l);
                ses.setGroup(g);
                ses.setId(sesid);
                a.setSession(ses);
                
                stu.setId(rs.getString("sid"));
                stu.setName(rs.getString("sname"));
                stu.setGender(rs.getBoolean("sgender"));
                a.setStudent(stu);
                
                a.setId(rs.getInt("aid"));
                
                if (a.getId() != 0) {
                    a.setIsPresent(rs.getBoolean("ispresent"));
                    a.setDescription(rs.getString("description"));
                    a.setDatetime(rs.getTimestamp("datetime"));
                }
                attends.add(a);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return attends;
    }
    
    public ArrayList<Student> getStudentBySession(int sesid) {
        ArrayList<Student> students = new ArrayList<>();
        try {
            String sql = "SELECT s.sid, s.sname\n"
                    + "FROM Student s \n"
                    + "INNER JOIN GroupStudent grs ON s.sid = grs.sid\n"
                    + "INNER JOIN [Group] g ON grs.gid = g.gid\n"
                    + "INNER JOIN [Session] ses ON g.gid = ses.gid\n"
                    + "WHERE ses.sesid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sesid);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getString("sid"));
                s.setName(rs.getString("sname"));
                students.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }
    
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
                    + "WHERE ses.lid = ? AND ses.date BETWEEN ? AND ?";
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
                sub.setId(rs.getString("subid"));
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
