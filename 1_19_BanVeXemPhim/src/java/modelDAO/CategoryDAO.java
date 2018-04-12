/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelDAO;

import java.sql.Connection;
import java.util.ArrayList;
import model.Category;
import model.Film;

/**
 *
 * @author NguyenNgoc
 */
public interface CategoryDAO {
    public boolean isExist(Connection con, Category c);

    public boolean addCategory(Connection con, Category c);

    public ArrayList<Category> getListCategory(Connection con);

    public boolean editCategory(Connection con, Category c);

    public Category getCategoryByName(Connection con, String name);
    
    public ArrayList<Category> getListCategoryByFilm(Connection con, Film film);
}
