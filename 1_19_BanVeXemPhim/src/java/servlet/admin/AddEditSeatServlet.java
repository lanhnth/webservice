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
import model.Room;
import model.Seat;
import modelDAO.SeatDAO;
import modelDAO.SeatDAOImpl;

/**
 *
 * @author ducvu
 */
public class AddEditSeatServlet extends HttpServlet {

    protected Connection con = DBConnection.getConnection();
    protected SeatDAO daoSeat = new SeatDAOImpl();

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
            out.println("<title>Servlet AddEditSeatServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddEditSeatServlet at " + request.getContextPath() + "</h1>");
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
                    String name = request.getParameter("name");
                    int col = Integer.parseInt(request.getParameter("col"));
                    int row = Integer.parseInt(request.getParameter("row"));
                    int id_room = Integer.parseInt(request.getParameter("id_room"));
                    String type = request.getParameter("type");
                    Room room = new Room();
                    room.setId(id_room);
                    Seat seat = new Seat( col, row, type, 0, room);

                    if (daoSeat.addSeat(con, seat)) {
                        response.sendRedirect("admin?controller=seat&alert=success");
                    } else {
                        response.sendRedirect("admin?controller=seat&alert=fail");
                    }
                    break;
                }
                case "edit": {
                    int id = Integer.parseInt(request.getParameter("id"));
                    int col = Integer.parseInt(request.getParameter("col"));
                    int row = Integer.parseInt(request.getParameter("row"));
                    int id_room = Integer.parseInt(request.getParameter("id_room"));
                    String type = request.getParameter("type");
                    Room room = new Room();
                    room.setId(id_room);
                    Seat seat = new Seat(col, row, type, id, room);

                    if (daoSeat.editSeat(con, seat)) {
                        response.sendRedirect("admin?controller=seat&alert=success");
                    } else {
                        response.sendRedirect("admin?controller=seat&alert=fail");
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
