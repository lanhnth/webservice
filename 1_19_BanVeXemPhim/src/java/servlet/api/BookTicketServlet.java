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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Client;
import model.Film;
import model.Order;
import model.Sale;
import model.Schedule;
import model.Seat;
import model.Ticket;
import modelDAO.CategoryDAO;
import modelDAO.CategoryDAOImpl;
import modelDAO.CinemaDAO;
import modelDAO.CinemaDAOImpl;
import modelDAO.FilmDAO;
import modelDAO.FilmDAOImpl;
import modelDAO.OrderDAO;
import modelDAO.OrderDAOImpl;
import modelDAO.RoomDAO;
import modelDAO.RoomDAOImpl;
import modelDAO.SaleDAO;
import modelDAO.SaleDAOImpl;
import modelDAO.ScheduleDAO;
import modelDAO.ScheduleDAOImpl;
import modelDAO.SeatDAO;
import modelDAO.SeatDAOImpl;
import modelDAO.TicketDAO;
import modelDAO.TicketDAOImpl;
import modelDAO.UserDAO;
import modelDAO.UserDAOImpl;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ducvu
 */
public class BookTicketServlet extends HttpServlet {

    protected Connection con = DBConnection.getConnection();
    protected UserDAO userDao = new UserDAOImpl();
    protected SaleDAO daoSale = new SaleDAOImpl();
    protected FilmDAO daoFilm = new FilmDAOImpl();
    protected ScheduleDAO daoSchedule = new ScheduleDAOImpl();
    protected RoomDAO daoRoom = new RoomDAOImpl();
    protected CinemaDAO daoCinema = new CinemaDAOImpl();
    protected SeatDAO daoSeat = new SeatDAOImpl();
    protected CategoryDAO daoCate = new CategoryDAOImpl();
    protected OrderDAO daoOrder = new OrderDAOImpl();
    protected TicketDAO daoTicket = new TicketDAOImpl();

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
            out.println("<title>Servlet BookTicketServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookTicketServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();

        JSONObject row = new JSONObject();
        try {

            DateFormat formatter = new SimpleDateFormat("HH:mm");
            Time time = new Time(formatter.parse(request.getParameter("time")).getTime());
            SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(formatterDate.parse(request.getParameter("date")).getTime());

            Film film = new Film();
            film.setId(Integer.parseInt(request.getParameter("film")));
            Schedule schedule = daoSchedule.getSchedule(con, film, date, time);

            Sale sale = daoSale.getSale(con, schedule.getSale().getId());

            int subtotal = Integer.parseInt(request.getParameter("subtotal"));

            Client client = (Client) session.getAttribute("user");
            System.out.println(client.getId());
            Date today = new Date(System.currentTimeMillis());
            Order order = new Order(0, today, 1, client, sale.getNumber() * subtotal, subtotal - sale.getNumber() * subtotal);

            daoOrder.addOrder(con, order);
            order.setId(daoOrder.getIdMax(con));
            String prime = request.getParameter("prime");
            String[] sea = prime.split(",");
            String[] type = {"A", "B", "C", "D", "E", "F", "G", "H"};
            for (int i = 0; i < sea.length; i++) {
                for (int j = 0; j < type.length; j++) {
                    int result = sea[i].lastIndexOf(type[j]);
                    if (result > -1) {
                        Seat seatt = daoSeat.getSeat(con, j + 1, Integer.parseInt(sea[i].substring(1, sea[i].length())));
                        Ticket ticket = new Ticket(0, schedule.getPrice(), schedule, order, seatt);
                        daoTicket.addTicket(con, ticket);
                        break;
                    }
                }
            }

            row.put("schedule", schedule.getId());
            row.put("order", order.getId());
        } catch (ParseException ex) {
            Logger.getLogger(BookTicketServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(BookTicketServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        PrintWriter outPrintWriter = response.getWriter();
        Gson g = new GsonBuilder().disableHtmlEscaping().create();
        g.toJson(row, outPrintWriter);
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
