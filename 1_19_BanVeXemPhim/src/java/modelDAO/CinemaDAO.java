/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelDAO;

import java.sql.Connection;
import java.util.ArrayList;
import model.Cinema;

/**
 *
 * @author NguyenNgoc
 */
public interface CinemaDAO {
    public boolean addCinema(Connection con, Cinema cinema);

    public ArrayList<Cinema> getListCinema(Connection con);
    
    public boolean editCinema(Connection con, Cinema cinema);
}
