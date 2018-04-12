/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelDAO;

import java.sql.Connection;
import java.util.ArrayList;
import model.Client;
import model.Order;

/**
 *
 * @author NguyenNgoc
 */
public interface OrderDAO {

    public ArrayList<Order> getOrderSucess(Connection con, Client client);

    public boolean addOrder(Connection con, Order order);

    public Order getOrder(Connection con, int id);

    public int getIdMax(Connection con);
}
