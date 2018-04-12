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
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Film;
import model.Schedule;
import model.Seat;
import modelDAO.CategoryDAO;
import modelDAO.CategoryDAOImpl;
import modelDAO.CinemaDAO;
import modelDAO.CinemaDAOImpl;
import modelDAO.FilmDAO;
import modelDAO.FilmDAOImpl;
import modelDAO.RoomDAO;
import modelDAO.RoomDAOImpl;
import modelDAO.SaleDAO;
import modelDAO.SaleDAOImpl;
import modelDAO.ScheduleDAO;
import modelDAO.ScheduleDAOImpl;
import modelDAO.SeatDAO;
import modelDAO.SeatDAOImpl;
import modelDAO.UserDAO;
import modelDAO.UserDAOImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ducvu
 */
public class SeatRoomServlet extends HttpServlet {

    protected Connection con = DBConnection.getConnection();
    protected UserDAO userDao = new UserDAOImpl();
    protected SaleDAO daoSale = new SaleDAOImpl();
    protected FilmDAO daoFilm = new FilmDAOImpl();
    protected ScheduleDAO daoSchedule = new ScheduleDAOImpl();
    protected RoomDAO daoRoom = new RoomDAOImpl();
    protected CinemaDAO daoCinema = new CinemaDAOImpl();
    protected SeatDAO daoSeat = new SeatDAOImpl();
    protected CategoryDAO daoCate = new CategoryDAOImpl();

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
            out.println("<title>Servlet SeatRoomServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SeatRoomServlet at " + request.getContextPath() + "</h1>");
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

        request.setCharacterEncoding("UTF8"); // this line solves the problem
        response.setContentType("application/json");
        JSONObject json = new JSONObject();
        try {
            DateFormat formatter = new SimpleDateFormat("HH:mm");
            Time time = new Time(formatter.parse(request.getParameter("time")).getTime());
            SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(formatterDate.parse(request.getParameter("date")).getTime());

            Film film = new Film();
            film.setId(Integer.parseInt(request.getParameter("film")));
            Schedule schedule = daoSchedule.getSchedule(con, film, date, time);
            
            Seat[] seats = schedule.getRoom().getSeats();
            
            String[] type = {"A","B","C","D","E","F","G","H"};
            int i = 1;
            String value = "";
            String category = "";
            JSONArray list_seat = new JSONArray();
            for (Seat seat: seats){
                int t = i;
                if (seat.getRow() == i){
                    value += type[seat.getRow()-1]+seat.getCol()+",";
                    category = seat.getType();
                }else {
                    JSONObject row = new JSONObject();
                    row.put("category",category);
                    value = value.substring(0,value.length()-1);
                    row.put("value",value);
                    list_seat.put(row);
                    value = "";
                    category = "";
                    i=seat.getRow();
                    value += type[seat.getRow()-1]+seat.getCol()+",";
                }
                
            }
            JSONObject row = new JSONObject();
            row.put("category",category);
            value = value.substring(0,value.length()-1);
            row.put("value",value);
            list_seat.put(row);
            int j = 1;
            String value1 = "";
            ArrayList<Seat> se = daoSchedule.getListTicket(con, schedule);
            for (Seat seat: se){
                if (seat.getRow() == j){
                    value1 += type[seat.getRow()-1]+seat.getCol()+",";
                }else {
                    j = seat.getRow();
                    value1 += type[seat.getRow()-1]+seat.getCol()+",";
                }
            }
            
            
            json.put("subtotal",0);
            json.put("list_seat",list_seat);
            json.put("seat_row",i);
            json.put("seat_prime",value1);
            json.put("seat_number",daoSchedule.getNumberSeat(con, schedule.getRoom().getId()));
            
            System.out.println(json);
        } catch (ParseException | JSONException ex) {
        }
        PrintWriter outPrintWriter = response.getWriter();
        Gson g = new GsonBuilder().disableHtmlEscaping().create();
        g.toJson(json, outPrintWriter);
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
        processRequest(request, response);
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
