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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Room;
import model.Seat;

/**
 *
 * @author NguyenNgoc
 */
public class RoomDAOImpl implements RoomDAO {

    @Override
    public boolean addRoom(Connection con, Room room) {
        try {
            String sql = "INSERT INTO room(name, type, cinema_id) VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, room.getName());
            ps.setString(2, room.getType());
            ps.setInt(3, room.getCinema_id());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SaleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public ArrayList<Room> getListRoom(Connection con) {
        ArrayList<Room> result = new ArrayList<Room>();
        try {
            String sql = "SELECT * FROM room";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Room room = new Room(
                        rs.getInt("id"), 
                        rs.getString("name"), 
                        rs.getString("type"),
                        rs.getInt("cinema_id"));
                result.add(room);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return result;
    }

    @Override
    public boolean editRoom(Connection con, Room room) {
        try {
            String sql = "UPDATE room SET name=?, type=?, cinema_id=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, room.getName());
            ps.setString(2, room.getType());
            ps.setInt(3, room.getCinema_id());
            ps.setInt(4, room.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SaleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public ArrayList<Room> getListInforRoom(Connection con) {
        ArrayList<Room> result = new ArrayList<Room>();
        try {
            String sql = "SELECT * FROM room";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Room room = new Room(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        null);
                result.add(room);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return result;
    }

    @Override
    public Room getRoom(Connection con, Room room) {
        Room room1 = new Room();
        Vector<Seat> ee = new Vector<Seat>();
        Seat[] result = null;
        try {
            String sql = "SELECT *,seat.id as seat_id,seat.type as seat_type FROM room,seat WHERE room.id = seat.room_id AND room.id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, room.getId());
            ResultSet rs = ps.executeQuery();
            int i=0;
            while (rs.next()) {
                i++;
                room1.setId(1);
                room1.setName(rs.getString("name"));
                room1.setType(rs.getString("name"));
                room1.setCinema_id(rs.getInt("cinema_id"));
                Seat a = new Seat(rs.getInt("col"), rs.getInt("row"), rs.getString("seat_type"), rs.getInt("seat_id"));
                ee.add(a);
            }
            result = new Seat[i];
            room1.setSeats(ee.toArray(result));
        } catch (SQLException ex) {
            Logger.getLogger(SaleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return room1;
    }

}
