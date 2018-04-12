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
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Film;
import model.Room;
import model.Sale;
import model.Schedule;
import modelDAO.ScheduleDAO;
import modelDAO.ScheduleDAOImpl;

/**
 *
 * @author ducvu
 */
public class AddEditScheduleServlet extends HttpServlet {

    protected Connection con = DBConnection.getConnection();
    protected ScheduleDAO daoSchedule = new ScheduleDAOImpl();

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
            out.println("<title>Servlet AddEditScheduleServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddEditScheduleServlet at " + request.getContextPath() + "</h1>");
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
                        DateFormat formatter = new SimpleDateFormat("HH:mm");
                        Time time = new Time(formatter.parse(request.getParameter("time")).getTime());
                        SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = new Date(formatterDate.parse(request.getParameter("date")).getTime());
                        Film film = new Film();
                        film.setId(Integer.parseInt(request.getParameter("film_id")));
                        Room room = new Room();
                        room.setId(Integer.parseInt(request.getParameter("room_id")));
                        Sale sale = new Sale();
                        sale.setId(Integer.parseInt(request.getParameter("sale_id")));
                        Double price = Double.parseDouble(request.getParameter("price"));
                        Schedule schedule = new Schedule(0, time, date, room, film, price, sale);
                        if (daoSchedule.addSchedule(con, schedule)){
                            response.sendRedirect("admin?controller=schedule&alert=success");
                        }else {
                            response.sendRedirect("admin?controller=schedule&alert=fail");
                        }
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                        response.sendRedirect("admin?controller=schedule&alert=fail");
                    }
                    break;
                }
                case "edit": {
                    try {
                        DateFormat formatter = new SimpleDateFormat("HH:mm");
                        Time time = new Time(formatter.parse(request.getParameter("time")).getTime());
                        SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = new Date(formatterDate.parse(request.getParameter("date")).getTime());
                        Film film = new Film();
                        film.setId(Integer.parseInt(request.getParameter("film_id")));
                        Room room = new Room();
                        room.setId(Integer.parseInt(request.getParameter("room_id")));
                        Sale sale = new Sale();
                        sale.setId(Integer.parseInt(request.getParameter("sale_id")));
                        Double price = Double.parseDouble(request.getParameter("price"));
                        int id = Integer.parseInt(request.getParameter("id"));
                        Schedule schedule = new Schedule(id, time, date, room, film, price, sale);
                        if (daoSchedule.editSchedule(con, schedule)){
                            response.sendRedirect("admin?controller=schedule&alert=success");
                        }else {
                            response.sendRedirect("admin?controller=schedule&alert=fail");
                        }
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                        response.sendRedirect("admin?controller=schedule&alert=fail");
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
