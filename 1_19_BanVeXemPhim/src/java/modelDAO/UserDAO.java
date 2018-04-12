/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelDAO;

import java.sql.Connection;
import model.User;

/**
 *
 * @author NguyenNgoc
 */
public interface UserDAO {
    public boolean checkLoginAdmin(Connection con, User user);
}
