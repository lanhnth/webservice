/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Film;
import model.Room;
import model.Sale;
import model.Schedule;
import model.Seat;

/**
 *
 * @author NguyenNgoc
 */
public class ScheduleDAOImpl implements ScheduleDAO {

    @Override
    public boolean addSchedule(Connection con, Schedule schedule) {
        try {
            String sql = "INSERT INTO schedule(time, date, price, sale_id, film_id, room_id) VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setTime(1, schedule.getTime());
            ps.setDate(2, (Date) schedule.getDate());
            ps.setDouble(3, schedule.getPrice());
            ps.setInt(4, schedule.getSale().getId());
            ps.setInt(5, schedule.getFilm().getId());
            ps.setInt(6, schedule.getRoom().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SaleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public ArrayList<Schedule> getListSchedule(Connection con) {
        ArrayList<Schedule> result = new ArrayList<Schedule>();
        try {
            String sql = "SELECT schedule.id, schedule.time as schedule_time, schedule.date as schedule_date,schedule.price as schedule_price, "
                    + "film.id as film_id, film.name as film_name, "
                    + "room.id as room_id, room.name as room_name, "
                    + "sale.id as sale_id, sale.name as sale_name "
                    + " FROM schedule "
                    + " INNER JOIN film ON schedule.film_id = film.id"
                    + " INNER JOIN room ON schedule.room_id = room.id"
                    + " INNER JOIN sale ON schedule.sale_id = sale.id"
                    + " ;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Time time = rs.getTime(2);
                Date date = (Date) rs.getDate(3);
                Double price = rs.getDouble(4);
                Film film = new Film(rs.getInt("film_id"), rs.getString("film_name"));
                Room room = new Room(rs.getInt("room_id"), rs.getString("room_name"));
                Sale sale = new Sale(rs.getInt("sale_id"), rs.getString("sale_name"));
                Schedule schudule = new Schedule(rs.getInt(1), time, date, room, film, price, sale);
                result.add(schudule);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FilmDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return result;
    }

    @Override
    public boolean editSchedule(Connection con, Schedule schedule) {
        try {
            String sql = "UPDATE schedule SET time=?, date=?, price=?, sale_id=?, film_id=?, room_id=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setTime(1, schedule.getTime());
            ps.setDate(2, (Date) schedule.getDate());
            ps.setDouble(3, schedule.getPrice());
            ps.setInt(4, schedule.getSale().getId());
            ps.setInt(5, schedule.getFilm().getId());
            ps.setInt(6, schedule.getRoom().getId());
            ps.setInt(7, schedule.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SaleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean delelteSchedule(Connection con, Schedule sale) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Schedule> getListScheduleByFilmDate(Connection con, Film film, Date date) {
        ArrayList<Schedule> result = new ArrayList<Schedule>();
        try {
            String sql = "SELECT *,room.id as room_id, room.name as room_name FROM schedule INNER JOIN room ON schedule.room_id = room.id WHERE schedule.film_id = ? AND schedule.date = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, film.getId());
            SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
            ps.setString(2, formatterDate.format(date));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Time time = rs.getTime("time");
//                    Date date1 = (Date) rs.getDate("date");
                Double price = rs.getDouble(4);
//                    Film film1 = new Film(rs.getInt("film_id"), rs.getString("film_name"));
                Room room = new Room(rs.getInt("room_id"), rs.getString("room_name"));
                Sale sale = new Sale();
                Schedule schudule = new Schedule(rs.getInt(1), time, date, room, film, price, sale);
                result.add(schudule);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FilmDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return result;
    }

    @Override
    public int getNumberSeat(Connection con, int room_id) {
        try {
            String sql = "SELECT COUNT(room.id) as count FROM seat, room WHERE room.id = seat.room_id AND room.id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, room_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("count");
            }
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(SeatDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public ArrayList<Integer> getListScheduleIdFilm(Connection con, Date date) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        try {
            String sql = "SELECT DISTINCT schedule.film_id as film_id FROM schedule WHERE schedule.date = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
            ps.setString(1, formatterDate.format(date));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(rs.getInt("film_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FilmDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return result;
    }

    @Override
    public Schedule getSchedule(Connection con, Film film, Date date, Time time) {
        Schedule schedule = null;
        try {
            String sql = "SELECT *,room.id as room_id, room.name as room_name,sale.id as sale_id, sale.name as sale_name FROM schedule INNER JOIN sale ON schedule.sale_id = sale.id INNER JOIN room ON schedule.room_id = room.id WHERE schedule.film_id = ? AND schedule.date = ? AND schedule.time = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, film.getId());
            SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
            ps.setString(2, formatterDate.format(date));
            ps.setTime(3, time);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Double price = rs.getDouble(4);
                Room room = new Room(rs.getInt("room_id"), rs.getString("room_name"));
                RoomDAO daoRoom = new RoomDAOImpl();
                Room getRoom = daoRoom.getRoom(con, room);
                Sale sale = new Sale(rs.getInt("sale_id"), rs.getString("sale_name"));
                schedule = new Schedule(rs.getInt(1), time, date, getRoom, film, price, sale);
            }
            return schedule;
        } catch (SQLException ex) {
            Logger.getLogger(FilmDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int getNumberSeated(Connection con, int room_id) {
        try {
            String sql = "SELECT COUNT(room.id) as count FROM seat,room,ticket,`order` as ordera WHERE room.id = seat.room_id AND room.id = ? AND ticket.seat_id = seat.id AND ordera.id = ticket.order_id AND ordera.status > 0";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, room_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("count");
            }
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(SeatDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public ArrayList<Seat> getListTicket(Connection con, Schedule schedule) {
        ArrayList<Seat> result = new ArrayList<Seat>();
        try {
            String sql = "SELECT seat.* FROM seat,room,ticket,`order` as ordera WHERE ticket.schedule_id = ? AND room.id = seat.room_id AND room.id = ? AND ticket.seat_id = seat.id AND ordera.id = ticket.order_id AND ordera.status > 0";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, schedule.getId());
            ps.setInt(2, schedule.getRoom().getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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
            Logger.getLogger(FilmDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return result;
    }

    @Override
    public Schedule getSchedule(Connection con, int id) {
        Schedule result = null;
        try {
            String sql = "SELECT schedule.id, schedule.time as schedule_time, schedule.date as schedule_date,schedule.price as schedule_price, "
                    + "film.id as film_id, film.name as film_name, "
                    + "room.id as room_id, room.name as room_name, "
                    + "sale.id as sale_id, sale.name as sale_name "
                    + " FROM schedule "
                    + " INNER JOIN film ON schedule.film_id = film.id"
                    + " INNER JOIN room ON schedule.room_id = room.id"
                    + " INNER JOIN sale ON schedule.sale_id = sale.id"
                    + " WHERE schedule.id = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Time time = rs.getTime(2);
                Date date = (Date) rs.getDate(3);
                Double price = rs.getDouble(4);
                Film film = new Film(rs.getInt("film_id"), rs.getString("film_name"));
                Room room = new Room(rs.getInt("room_id"), rs.getString("room_name"));
                Sale sale = new Sale(rs.getInt("sale_id"), rs.getString("sale_name"));
                Schedule schudule = new Schedule(rs.getInt(1), time, date, room, film, price, sale);
                result= (Schedule) schudule;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FilmDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return result;
    }

    @Override
    public ArrayList<Seat> getListTicket(Connection con, Schedule schedule, int client, int order) {
        ArrayList<Seat> result = new ArrayList<Seat>();
        try {
            String sql = "SELECT seat.* FROM seat,room,ticket,`order` as ordera WHERE ticket.schedule_id = ? AND room.id = seat.room_id AND room.id = ? AND ticket.seat_id = seat.id AND ordera.id = ticket.order_id AND ordera.status > 0 AND ordera.client_id = ? AND ordera.id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, schedule.getId());
            ps.setInt(2, schedule.getRoom().getId());
            ps.setInt(3, client);
            ps.setInt(4, order);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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
            Logger.getLogger(FilmDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return result;
    }

}
