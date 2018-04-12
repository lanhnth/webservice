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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Sale;

/**
 *
 * @author NguyenNgoc
 */
public class SaleDAOImpl implements SaleDAO {

    @Override
    public boolean addSale(Connection con, Sale sale) {
        try {
            String sql = "INSERT INTO sale(name, sdate, edate, number, des) VALUES(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, sale.getName());
            ps.setDate(2, (Date) sale.getSdate());
            ps.setDate(3, (Date) sale.getEdate());
            ps.setFloat(4, sale.getNumber());
            ps.setString(5, sale.getDes());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SaleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public ArrayList<Sale> getListSale(Connection con) {
        ArrayList<Sale> result = new ArrayList<Sale>();
        try {
            String sql = "SELECT * FROM sale";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Sale sale = new Sale(rs.getInt("id"), 
                        rs.getString("name"), 
                        rs.getDate("sdate"), 
                        rs.getDate("edate"), 
                        rs.getFloat("number"), 
                        rs.getString("des"));
                result.add(sale);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean editSale(Connection con, Sale sale) {
        try {
            String sql = "UPDATE sale SET name=?, sdate=?, edate=?, number=?, des=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, sale.getName());
            ps.setDate(2, sale.getSdate());
            ps.setDate(3, sale.getEdate());
            ps.setFloat(4, sale.getNumber());
            ps.setString(5, sale.getDes());
            ps.setInt(6, sale.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SaleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Sale getSale(Connection con, int id) {
        Sale result = null;
        try {
            String sql = "SELECT * FROM sale WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Sale sale = new Sale(rs.getInt("id"), 
                        rs.getString("name"), 
                        rs.getDate("sdate"), 
                        rs.getDate("edate"), 
                        rs.getFloat("number"), 
                        rs.getString("des"));
                result= (Sale) sale;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return result;
    }

}
