/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelDAO;

import java.sql.Connection;
import java.util.ArrayList;
import model.Seat;

/**
 *
 * @author NguyenNgoc
 */
public interface SeatDAO {
    public boolean addSeat(Connection con, Seat seat);

    public ArrayList<Seat> getListSeat(Connection con);
    
    public ArrayList<Seat> getListRowSeat(Connection con,int row, int room_id);
    
    public boolean editSeat(Connection con, Seat seat);
    
    public Seat getSeat(Connection con, int row, int col);
}
