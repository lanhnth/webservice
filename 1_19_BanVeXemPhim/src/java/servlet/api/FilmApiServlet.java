/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import control.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;
import model.Film;
import modelDAO.CategoryDAO;
import modelDAO.CategoryDAOImpl;
import modelDAO.FilmDAO;
import modelDAO.FilmDAOImpl;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ducvu
 */
@WebServlet(name = "FilmApiServlet", urlPatterns = {"/filmApi"})
public class FilmApiServlet extends HttpServlet {

    protected Connection con = DBConnection.getConnection();
    protected CategoryDAO daoCate = new CategoryDAOImpl();
    protected FilmDAO daoFilm = new FilmDAOImpl();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FilmApiServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FilmApiServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF8"); // this line solves the problem
        response.setContentType("application/json");
        System.out.println("Name: " + request.getParameter("name"));
//        Book book = daoBook.searchById(id);
//        if (book != null) {
        PrintWriter outPrintWriter = response.getWriter();
        Gson g = new GsonBuilder().disableHtmlEscaping().create();
        g.toJson("done", outPrintWriter);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF8"); // this line solves the problem
        response.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        String jsonString = IOUtils.toString(request.getInputStream());
        try {
            JSONObject json = new JSONObject(jsonString);
            System.out.println("Film: " + json + " \n");
            ArrayList<Category> categories = new ArrayList<Category>();
            SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
            Date release_date = new Date(formatterDate.parse(json.getString("release_date")).getTime());
            Double t = json.getDouble("vote");
            Float vote = Float.parseFloat(t.toString());
            int runtime = json.getInt("runtime");
            String des = json.getString("des");
            Film film = new Film(
                    0,
                    json.getString("name"),
                    "",
                    release_date,
                    runtime,
                    vote,
                    json.getInt("api_id"),
                    des,
                    json.getString("trailler"),
                    json.getString("poster"),
                    categories
            );
            JSONArray genres = json.getJSONArray("category");
            
            for (int i = 0; i < genres.length(); i++) {
                Category c = new Category(genres.getJSONObject(i).getString("name"), "");
                if (!daoCate.isExist(con, c)) {
                    daoCate.addCategory(con, c);

                    System.out.println("Add Category: " + c.getName());
                    
                    categories.add(daoCate.getCategoryByName(con, c.getName()));
                } else {
                    System.out.println("Loi Category");
                    
                }
            }
            
            if (!daoFilm.isExist(con, film)) {
                daoFilm.addFilmApi(con, film);
                System.out.println("Add Film: " + film.getName());
            } else {
                System.out.println("Loi Film");
            }

        } catch (JSONException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(FilmApiServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        PrintWriter outPrintWriter = response.getWriter();
        Gson g = new GsonBuilder().disableHtmlEscaping().create();
        g.toJson("done", outPrintWriter);
//        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
