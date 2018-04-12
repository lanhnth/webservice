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
import model.Ticket;

/**
 *
 * @author NguyenNgoc
 */
public class TicketDAOImpl implements TicketDAO{

    @Override
    public boolean addTicket(Connection con, Ticket ticket) {
        try {
            String sql = "INSERT INTO ticket(price, schedule_id, order_id, seat_id) VALUES(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1,ticket.getPrice());
            ps.setInt(2, ticket.getSchedule().getId());
            ps.setInt(3, ticket.getOrder().getId());
            ps.setInt(4, ticket.getSeat().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SeatDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    
}
