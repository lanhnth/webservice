/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelDAO;

import java.sql.Connection;
import java.util.ArrayList;
import model.Ticket;

/**
 *
 * @author NguyenNgoc
 */
public interface TicketDAO {
    public boolean addTicket(Connection con, Ticket ticket);
}
