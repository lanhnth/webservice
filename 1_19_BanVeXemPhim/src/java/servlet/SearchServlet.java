/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import control.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Category;
import model.Film;
import modelDAO.CategoryDAO;
import modelDAO.CategoryDAOImpl;
import modelDAO.FilmDAO;
import modelDAO.FilmDAOImpl;

/**
 *
 * @author Dell
 */
public class SearchServlet extends HttpServlet {

    Connection con = DBConnection.getConnection();
    CategoryDAO daoCate = new CategoryDAOImpl();
    ArrayList<Category> listCate = daoCate.getListCategory(con);
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
            out.println("<title>Servlet SearchServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchServlet at " + request.getContextPath() + "</h1>");
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
        String cate = request.getParameter("cate");
        System.out.println(cate);
        FilmDAO dao = new FilmDAOImpl();
        ArrayList<Film> list = dao.getFilmByCategory(con, cate);
        System.out.println();
        HttpSession session = request.getSession();
        session.setAttribute("search", list);
        session.setAttribute("cate", listCate);
        response.sendRedirect("home?controller=search");
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
        String text = request.getParameter("textsearch");
        System.out.println(text);
        FilmDAO dao = new FilmDAOImpl();
        ArrayList<Film> list = list = dao.getFilmByName(con, text);
        
        HttpSession session = request.getSession();
        session.setAttribute("search", list);
        session.setAttribute("cate", listCate);
        response.sendRedirect("home?controller=search");
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
