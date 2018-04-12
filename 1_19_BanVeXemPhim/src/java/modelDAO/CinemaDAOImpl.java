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
import model.Cinema;

/**
 *
 * @author NguyenNgoc
 */
public class CinemaDAOImpl implements CinemaDAO{

    @Override
    public boolean addCinema(Connection con, Cinema cinema) {
        try {
            String sql = "INSERT INTO cinema(name, address) VALUES(?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cinema.getName());
            ps.setString(2, cinema.getAddress());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SaleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public ArrayList<Cinema> getListCinema(Connection con) {
        ArrayList<Cinema> result = new ArrayList<Cinema>();
        try {
            String sql = "SELECT * FROM cinema";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Cinema cinema = new Cinema(
                        rs.getInt("id"), 
                        rs.getString("name"), 
                        rs.getString("address"));
                result.add(cinema);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return result;
    }

    @Override
    public boolean editCinema(Connection con, Cinema cinema) {
        try {
            String sql = "UPDATE cinema SET name=?, address=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cinema.getName());
            ps.setString(2, cinema.getAddress());
            ps.setInt(3, cinema.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SaleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
