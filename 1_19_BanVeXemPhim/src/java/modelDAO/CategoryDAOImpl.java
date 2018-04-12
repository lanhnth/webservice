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
import model.Category;
import model.Film;

/**
 *
 * @author NguyenNgoc
 */
public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public boolean addCategory(Connection con, Category c) {
        try {
            String sql = "INSERT INTO category (name, description) VALUES (?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getName());
            ps.setString(2, c.getDes());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public ArrayList<Category> getListCategory(Connection con) {
        ArrayList<Category> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM category";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category c = new Category(rs.getInt(1), rs.getString(2), rs.getString(3));
                result.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean editCategory(Connection con, Category c) {
        try {
            String sql = "UPDATE category SET name=?, description=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getName());
            ps.setString(2, c.getDes());
            ps.setInt(3, c.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public ArrayList<Category> getListCategoryByFilm(Connection con, Film film) {
        ArrayList<Category> result = new ArrayList<Category>();
        try {
            String sql = "SELECT category.id, category.name, category.description FROM category, category_film, film\n"
                    + "WHERE category_film.filmid = film.id AND category_film.categoryid = category.id AND film.id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, film.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                result.add(new Category(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean isExist(Connection con, Category c) {
        try {
            String sql = "SELECT * FROM category WHERE name = ? ;";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, c.getName());
            ResultSet rs = pre.executeQuery();
            if (rs.next()){
                int t = rs.getInt(1);
                System.out.println("ID category:"+t);
                return t>0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Loi SQL Category");
//            return false;
        }
        return false;
    }

    @Override
    public Category getCategoryByName(Connection con, String name) {
        Category result = new Category();
        try {
            String sql = "SELECT * FROM category WHERE name = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category c = new Category(rs.getInt(1), rs.getString(2), rs.getString(3));
                result =  (Category) c ;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return result;
    }

}
