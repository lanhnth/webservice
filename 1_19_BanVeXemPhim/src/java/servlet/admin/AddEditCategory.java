/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.admin;

import control.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;
import modelDAO.CategoryDAO;
import modelDAO.CategoryDAOImpl;

/**
 *
 * @author NguyenNgoc
 */
public class AddEditCategory extends HttpServlet {

    protected CategoryDAO daoCa = new CategoryDAOImpl();
    protected Connection con = DBConnection.getConnection();
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
            out.println("<title>Servlet AddEditCategory</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddEditCategory at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        if (request.getParameter("action") != null) {
            String action = request.getParameter("action");
            switch (action) {
                case "add": {
                    String name = request.getParameter("nameCategory");
                    String des = request.getParameter("des");
                    Category c = new Category(name, des);
                    if(daoCa.addCategory(con, c)){
                        request.setAttribute("listCategory", daoCa.getListCategory(con));
                        response.sendRedirect("admin?controller=category");
                    }else{
                        response.sendRedirect("admin?controller=category&alert=fail");
                    }
                    break;
                }
                case "edit": {
                    String nameEdit = request.getParameter("nameCategoryEdit");
                    String desEdit = request.getParameter("desEdit");
                    int id = Integer.parseInt(request.getParameter("idCateEdit"));
                    Category c = new Category(id, nameEdit, desEdit);
                    if(daoCa.editCategory(con, c)){
                        request.setAttribute("listCategory", daoCa.getListCategory(con));
                        response.sendRedirect("admin?controller=category");
                    }else{
                        response.sendRedirect("admin?controller=category&alert=fail");
                    }
                    break;
                }
                case "delete": {
                    break;
                }
                default: {

                }
            }
        }
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
