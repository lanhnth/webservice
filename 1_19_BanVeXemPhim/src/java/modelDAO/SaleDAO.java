/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelDAO;

import java.sql.Connection;
import java.util.ArrayList;
import model.Sale;

/**
 *
 * @author NguyenNgoc
 */
public interface SaleDAO {

    public boolean addSale(Connection con, Sale sale);

    public ArrayList<Sale> getListSale(Connection con);
    
    public Sale getSale(Connection con, int id);
    
    public boolean editSale(Connection con, Sale sale);
}
