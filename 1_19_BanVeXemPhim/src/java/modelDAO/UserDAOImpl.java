/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelDAO;

import control.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import static modelDAO.ClientDAOImpl.encryption;

/**
 *
 * @author NguyenNgoc
 */
public class UserDAOImpl implements UserDAO {
    
    /**
     *
     * @param user
     * @return
     */
    @Override
    public boolean checkLoginAdmin(Connection con, User user) {
        try {
            String sql = "SELECT * FROM user WHERE username = ? and password = ? ;";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, user.getUsername());
            pre.setString(2, user.getPassword());
            ResultSet rs = pre.executeQuery();
            if (rs.next()){
                int t = rs.getInt(5);
                return t >= 1;
            }
            return false;
        } catch (SQLException ex) {
            return false;
        }
    }

}
