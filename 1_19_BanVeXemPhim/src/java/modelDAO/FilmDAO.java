/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelDAO;

import java.sql.Connection;
import java.util.ArrayList;
import model.Film;

/**
 *
 * @author NguyenNgoc
 */
public interface FilmDAO {
    public boolean isExist(Connection con, Film film);
    
    public boolean addFilm(Connection con, Film film);
    
    public boolean addFilmApi(Connection con, Film film);
    
    public ArrayList<Film> getListFilm(Connection con);
    
    public Film getFilm(Connection con, int id);
    
    public boolean updateFilm(Connection con, Film film);
    
    public ArrayList<Film> getFilmByName(Connection con, String name);
    
    public ArrayList<Film> getFilmByCategory(Connection con, String category);
    
    public ArrayList<Film> getListPreFilm(Connection con);
}
