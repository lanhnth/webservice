/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Room;
import model.Seat;

/**
 *
 * @author NguyenNgoc
 */
public class SeatDAOImpl implements SeatDAO{

    @Override
    public boolean addSeat(Connection con, Seat seat) {
        try {
            String sql = "INSERT INTO seat(col, row, type, room_id) VALUES(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,seat.getCol());
            ps.setInt(2, seat.getRow());
            ps.setString(3, seat.getType());
            ps.setInt(4, seat.getRoom().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SeatDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public ArrayList<Seat> getListSeat(Connection con) {
        ArrayList<Seat> result = new ArrayList<Seat>();
        try {
            String sql = "SELECT * FROM seat";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Room room = new Room(rs.getInt("room_id"), "");
                Seat seat = new Seat(
                        rs.getInt("row"), 
                        rs.getInt("col"), 
                        rs.getString("type"), 
                        rs.getInt("id"), 
                        room);
                result.add(seat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SeatDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return result;
    }

    @Override
    public boolean editSeat(Connection con, Seat seat) {
        try {
            String sql = "UPDATE seat SET col=?, row=?, type=?, room_id=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,seat.getCol());
            ps.setInt(2, seat.getRow());
            ps.setString(3, seat.getType());
            ps.setInt(4, seat.getRoom().getId());
            ps.setInt(5,seat.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SeatDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public ArrayList<Seat> getListRowSeat(Connection con, int row, int room_id) {
        ArrayList<Seat> result = new ArrayList<Seat>();
        try {
            String sql = "SELECT * FROM seat WHERE seat.room_id = ? AND seat.row = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, room_id);
            ps.setInt(2, row);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Room room = new Room(rs.getInt("room_id"), "");
                Seat seat = new Seat(
                        rs.getInt("row"), 
                        rs.getInt("col"), 
                        rs.getString("type"), 
                        rs.getInt("id"), 
                        room);
                result.add(seat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SeatDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return result;
    }

    @Override
    public Seat getSeat(Connection con, int row, int col) {
        Seat a = null;
        try {
            String sql = "SELECT * FROM seat WHERE seat.col = ? AND seat.row = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, col);
            ps.setInt(2, row);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Room room = new Room(rs.getInt("room_id"), "");
                Seat seat = new Seat(
                        rs.getInt("row"), 
                        rs.getInt("col"), 
                        rs.getString("type"), 
                        rs.getInt("id"), 
                        room);
                a = (Seat) seat;
            }
            return a;
        } catch (SQLException ex) {
            Logger.getLogger(SeatDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
