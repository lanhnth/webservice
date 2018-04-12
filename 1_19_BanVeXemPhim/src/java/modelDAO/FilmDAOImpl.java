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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.Film;

/**
 *
 * @author NguyenNgoc
 */
public class FilmDAOImpl implements FilmDAO {

    @Override
    public boolean addFilm(Connection con, Film film) {
        try {
            String sql = "INSERT INTO film(name, productCountry, releaseDate, runningtime, vote, des, trailer, poster, api_id) VALUES(?, ?, ?, ?, ?, ?, ?,  ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, film.getName());
            ps.setString(2, film.getProductCountry());
            ps.setDate(3, film.getRealeaseDate());
            ps.setInt(4, film.getRunningTime());
            ps.setFloat(5, film.getVote());
            ps.setString(6, film.getDes());
            ps.setString(7, film.getTrailer());
            ps.setString(8, film.getPoster());
            ps.setInt(9, 0);
            ps.executeUpdate();
            for (Category c : film.getCategories()) {
                addCategoryFilm(con, DBConnection.getMaxIDOfTable("film"), c.getId());
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FilmDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean addCategoryFilm(Connection con, int idFilm, int idCategory) {
        try {
            String sql = "INSERT INTO category_film(filmid, categoryid) VALUES(?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idFilm);
            ps.setInt(2, idCategory);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FilmDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public ArrayList<Film> getListFilm(Connection con) {
        ArrayList<Film> result = new ArrayList<Film>();
        try {
            String sql = "SELECT * FROM film";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Film a = new Film(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4),
                        rs.getInt(5),
                        rs.getFloat(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9));
                result.add(a);
            }
            CategoryDAO daoCate = new CategoryDAOImpl();
            for (Film f : result) {
                f.setCategories(daoCate.getListCategoryByFilm(con, f));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FilmDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean updateFilm(Connection con, Film film) {
        try {
            String sql = "UPDATE film SET name=?, productCountry=?, releaseDate=?, runningtime=?, vote=?, des=?, trailer=?, poster=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, film.getName());
            ps.setString(2, film.getProductCountry());
            ps.setDate(3, film.getRealeaseDate());
            ps.setInt(4, film.getRunningTime());
            ps.setFloat(5, film.getVote());
            ps.setString(6, film.getDes());
            ps.setString(7, film.getTrailer());
            ps.setString(8, film.getPoster());
            ps.setInt(9, film.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FilmDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean isExist(Connection con, Film film) {
        try {
            String sql = "SELECT * FROM film WHERE api_id = ? ;";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, film.getApi_id());
            ResultSet rs = pre.executeQuery();
            if (rs.next()){
                int t = rs.getInt(1);
                System.out.println("ID film:"+t);
                return t>0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Loi SQL");

//            return false;
        }
        return false;

    }

    @Override
    public boolean addFilmApi(Connection con, Film film) {
        try {
            String sql = "INSERT INTO film(name, productCountry, releaseDate, runningtime, vote, des, trailer, poster, api_id) VALUES(?, ?, ?, ?, ?, ?, ?,  ?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, film.getName());
            ps.setString(2, film.getProductCountry());
            ps.setDate(3, film.getRealeaseDate());
            ps.setInt(4, film.getRunningTime());
            ps.setFloat(5, film.getVote());
            ps.setString(6, film.getDes());
            ps.setString(7, film.getTrailer());
            ps.setString(8, film.getPoster());
            ps.setInt(9, film.getApi_id());
            ps.executeUpdate();
            for (Category c : film.getCategories()) {
                addCategoryFilm(con, DBConnection.getMaxIDOfTable("film"), c.getId());
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FilmDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Film getFilm(Connection con, int id) {
        try {
            String sql = "SELECT * FROM film WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Film result = null;
            while (rs.next()) {
                Film a = new Film(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4),
                        rs.getInt(5),
                        rs.getFloat(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9));
                CategoryDAO daoCate = new CategoryDAOImpl();
                a.setCategories(daoCate.getListCategoryByFilm(con, a));
                result = (Film) a;
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(FilmDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    /**
     *  get list film by name
     * @param con is a connection, get connection to database
     * @param name is a film name must search
     * @return list film by name
     */
    @Override
    public ArrayList<Film> getFilmByName(Connection con, String name) {
        ArrayList<Film> result = new ArrayList<Film>();
        try {
            String sql = "SELECT * FROM film where name like ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%"+name+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Film a = new Film(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4),
                        rs.getInt(5),
                        rs.getFloat(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9));
                
                a.setApi_id(rs.getInt(10));
                result.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FilmDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    /**
     * get list film by category
     * @param con is a connection, get connection to database
     * @param category is a category name must search
     * @return list film have category 
     */
    @Override
    public ArrayList<Film> getFilmByCategory(Connection con, String category) {
        ArrayList<Film> result = new ArrayList<Film>();
        try {
            String sql = "SELECT film.* FROM film, category_film, category "
                    + "WHERE film.id = category_film.filmid "
                    + "AND category_film.categoryid = category.id "
                    + "AND category.name = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, category);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Film a = new Film(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4),
                        rs.getInt(5),
                        rs.getFloat(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9));
                a.setApi_id(rs.getInt(10));
                result.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FilmDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<Film> getListPreFilm(Connection con) {
        ArrayList<Film> result = new ArrayList<Film>();
        try {
            String sql = "SELECT * FROM film WHERE api_id = 0";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Film film = new Film(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getInt(5), rs.getFloat(6), rs.getString(7), rs.getString(8), rs.getString(9));
                CategoryDAO dao = new CategoryDAOImpl();
                ArrayList<Category> categories = dao.getListCategoryByFilm(con, film);
                film.setCategories(categories);
                result.add(film);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FilmDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
