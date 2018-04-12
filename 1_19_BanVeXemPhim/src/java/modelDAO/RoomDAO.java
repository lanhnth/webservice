/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelDAO;

import java.sql.Connection;
import java.util.ArrayList;
import model.Room;

/**
 *
 * @author NguyenNgoc
 */
public interface RoomDAO {
    public boolean addRoom(Connection con, Room room);

    public ArrayList<Room> getListRoom(Connection con);
    
    public Room getRoom(Connection con, Room room);
    
    public ArrayList<Room> getListInforRoom(Connection con);
    
    public boolean editRoom(Connection con, Room room);
}
