/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NguyenNgoc
 */
public class DBConnection {
    static Connection con;
    static String url;
    static String username = "root";
    static String password = "root";

    public static Connection getConnection() {
        if (con != null) {
            return con;
        }
        try {
            String url = "jdbc:mysql://localhost:3306/ticket";
            Class.forName("com.mysql.jdbc.Driver");
            try {
                con = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }

        return con;
    }

    public static int getMaxIDOfTable(String table) {
        int max = 0;
        String sql = "Select max(id) from " + table;
        if (con == null) {
            con = getConnection();
        }
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            rs.beforeFirst();
            rs.next();
            max = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return max;
    }
    
}
