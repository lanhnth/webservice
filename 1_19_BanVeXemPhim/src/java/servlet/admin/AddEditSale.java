/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.admin;

import com.sun.istack.internal.logging.Logger;
import control.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Sale;
import modelDAO.SaleDAO;
import modelDAO.SaleDAOImpl;

/**
 *
 * @author NguyenNgoc
 */
public class AddEditSale extends HttpServlet {

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
            out.println("<title>Servlet AddEditSale</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddEditSale at " + request.getContextPath() + "</h1>");
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
                    try {
                        String nameSale = request.getParameter("name");
                        float number = Float.parseFloat(request.getParameter("number"));
                        String des = request.getParameter("des");
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        java.sql.Date sdate = new java.sql.Date(formatter.parse(request.getParameter("sdate")).getTime());
                        java.sql.Date edate = new java.sql.Date(formatter.parse(request.getParameter("edate")).getTime());
                        Sale sale = new Sale();
                        sale.setName(nameSale);
                        sale.setNumber(number);
                        sale.setDes(des);
                        sale.setSdate(sdate);
                        sale.setEdate(edate);
                        SaleDAO dao = new SaleDAOImpl();
                        Connection con = DBConnection.getConnection();
                        if (dao.addSale(con, sale)) {
                            request.setAttribute("listSale", dao.getListSale(con));
                            response.sendRedirect("admin?controller=sale");
                        } else {
                            response.sendRedirect("admin?controller=sale&alert=fail");
                        }
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                    break;
                }
                case "edit": {
                    try {
//            int select = Integer.parseInt(request.getAttribute("saleSelected").toString());
//            System.out.println(select);
                        int id = Integer.parseInt(request.getParameter("idEdit"));
                        System.out.println(id);
                        String name = request.getParameter("nameEdit");
                        System.out.println(name);
                        float number = Float.parseFloat(request.getParameter("numberEdit"));
                        String des = request.getParameter("desEdit");
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        java.sql.Date sdate = new java.sql.Date(formatter.parse(request.getParameter("sdateEdit")).getTime());
                        java.sql.Date edate = new java.sql.Date(formatter.parse(request.getParameter("edateEdit")).getTime());
                        Sale newSale = new Sale(id, name, sdate, edate, number, des);
                        Connection con = DBConnection.getConnection();
                        SaleDAO daoSale = new SaleDAOImpl();
                        if (daoSale.editSale(con, newSale)) {
                            request.setAttribute("listSale", daoSale.getListSale(con));
                            response.sendRedirect("admin?controller=sale");
                        } else {
                            response.sendRedirect("admin");
                        }
                    } catch (ParseException ex) {
                        ex.printStackTrace();
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
