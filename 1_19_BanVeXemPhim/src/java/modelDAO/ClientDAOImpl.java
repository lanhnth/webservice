/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelDAO;

import control.DBConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Client;

/**
 *
 *
 */
public class ClientDAOImpl implements ClientDAO {

    /**
     * get connection to database
     */
    public Connection con = new DBConnection().getConnection();

    /**
     * sigin new account
     *
     * @param client is a account sigin
     */
    @Override
    public void signin(Client client) {
        String sql = "INSERT INTO client(username, password, name, address, mail, phone) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, client.getUsername());
            pr.setString(2, encryption(client.getPassword()));
            pr.setString(3, client.getName());
            pr.setString(4, client.getAddress());
            pr.setString(5, client.getMail());
            pr.setString(6, client.getPhone());
            pr.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Check client login
     *
     * @param client
     * @return client
     */
    @Override
    public Client checkLoginClient(Client client) {
        String sql = "SELECT * FROM client where username = ?";
        try {
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, client.getUsername());
            ResultSet rs = pr.executeQuery();
            System.out.println(client.getUsername());
            Client x = new Client();
            while (rs.next()) {
                x.setId(rs.getInt("id"));
                x.setUsername(rs.getString("username"));
                x.setPassword(rs.getString("password"));
                x.setName(rs.getString("name"));
                x.setAddress(rs.getString("address"));
                x.setMail(rs.getString("mail"));
                x.setPhone(rs.getString("phone"));

                if (rs.getString("password").equals(encryption(client.getPassword()))) {
                    x.setPassword(client.getPassword());
                    return x;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

        return null;

    }

    /**
     * encryption password with md5
     *
     * @param str is password of client
     * @return password after md5 encryption
     */
    public static String encryption(String str) {
        byte[] defaultBytes = str.getBytes();
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(defaultBytes);
            byte messageDigest[] = algorithm.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String hex = Integer.toHexString(0xFF & messageDigest[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            str = hexString + "";
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * Change password of client
     *
     * @param client is client want change password
     * @param pass is new password
     */
    @Override
    public void changePassClient(Client client, String pass) {
        try {
            String sql = "UPDATE client SET password = ? WHERE username = ?";
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, encryption(pass));
            pr.setString(2, client.getUsername());
            pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Client socialLogin(String email, String name) {
        Client x = new Client();
        try {
            
            x.setMail(email);
            x.setUsername(email);
            x.setName(name);
            int id=0;
            boolean check = false;
            String sql = "SELECT * FROM client WHERE username = ? OR mail = ?";
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, email);
            pr.setString(2, email);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                check = true;
            }
            if (!check) {
                String sql1 = "INSERT INTO client(username, name, mail) VALUES (?,?,?)";
                PreparedStatement pr1 = con.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
                pr1.setString(1, email);
                pr1.setString(2, name);
                pr1.setString(3, email);
                pr1.executeUpdate();
                ResultSet rs1 = pr1.getGeneratedKeys();
                if (rs1.next()) {
                    id = rs1.getInt(1);
                }
                
            }
            x.setId(id);
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }

    @Override
    public void changeProfile(Client client) {
        try {
            String sql = "UPDATE client SET name = ?, address = ?, phone = ? WHERE id = ?";
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, client.getName());
            pr.setString(2, client.getAddress());
            pr.setString(3, client.getPhone());
            pr.setInt(4, client.getId());
            pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Client getClient(int id) {
        Client x = new Client();
        String sql = "SELECT * FROM client where id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                x.setId(rs.getInt("id"));
                x.setUsername(rs.getString("username"));
                x.setPassword(rs.getString("password"));
                x.setName(rs.getString("name"));
                x.setAddress(rs.getString("address"));
                x.setMail(rs.getString("mail"));
                x.setPhone(rs.getString("phone"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return x;
    }
}
