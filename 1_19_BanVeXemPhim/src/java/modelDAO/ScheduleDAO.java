/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import model.Film;
import model.Schedule;
import model.Seat;

/**
 *
 * @author NguyenNgoc
 */
public interface ScheduleDAO {

    public boolean addSchedule(Connection con, Schedule sale);

    public ArrayList<Schedule> getListSchedule(Connection con);
    
    public Schedule getSchedule(Connection con, int id);
    
    public ArrayList<Seat> getListTicket(Connection con,Schedule schedule);
    
    public ArrayList<Seat> getListTicket(Connection con,Schedule schedule, int client, int order);

    public ArrayList<Integer> getListScheduleIdFilm(Connection con, Date date);

    public ArrayList<Schedule> getListScheduleByFilmDate(Connection con, Film film, Date date);

    public boolean editSchedule(Connection con, Schedule sale);

    public int getNumberSeat(Connection con, int room_id);
    
    public int getNumberSeated(Connection con, int room_id);

    public boolean delelteSchedule(Connection con, Schedule sale);

    public Schedule getSchedule(Connection con,Film film, Date date, Time time);
}
