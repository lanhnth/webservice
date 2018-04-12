/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

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
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Client;
import model.Film;
import model.Order;
import model.Schedule;
import model.Seat;
import modelDAO.CategoryDAO;
import modelDAO.CategoryDAOImpl;
import modelDAO.CinemaDAO;
import modelDAO.CinemaDAOImpl;
import modelDAO.RoomDAO;
import modelDAO.RoomDAOImpl;
import modelDAO.SaleDAO;
import modelDAO.SaleDAOImpl;
import modelDAO.ScheduleDAO;
import modelDAO.ScheduleDAOImpl;
import modelDAO.SeatDAO;
import modelDAO.SeatDAOImpl;
import modelDAO.FilmDAO;
import modelDAO.FilmDAOImpl;
import modelDAO.OrderDAO;
import modelDAO.OrderDAOImpl;

/**
 *
 * @author ducvu
 */
public class HomeServlet extends HttpServlet {

    protected Connection con = DBConnection.getConnection();
    protected SaleDAO daoSale = new SaleDAOImpl();
    protected FilmDAO daoFilm = new FilmDAOImpl();
    protected ScheduleDAO daoSchedule = new ScheduleDAOImpl();
    protected RoomDAO daoRoom = new RoomDAOImpl();
    protected CinemaDAO daoCinema = new CinemaDAOImpl();
    protected SeatDAO daoSeat = new SeatDAOImpl();
    protected CategoryDAO daoCate = new CategoryDAOImpl();
    protected OrderDAO daoOrder = new OrderDAOImpl();

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
            out.println("<title>Servlet HomeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();

        if (request.getParameter("controller") != null) {
            String controller = request.getParameter("controller");
            //schedule 
            switch (controller) {
                case "schedule": {
//                    ArrayList<Map<Date, ArrayList<Map<Film, ArrayList<Schedule>>>>> listAll = new ArrayList<>();
                    Map<Date, ArrayList<Map<Film, ArrayList<Schedule>>>> all = new HashMap<>();
                    ArrayList<Date> listDate = getListDate();

                    for (Date date : listDate) {
                        ArrayList<Integer> listIdFilm = daoSchedule.getListScheduleIdFilm(con, date);
                        ArrayList<Map<Film, ArrayList<Schedule>>> filmSchedule = new ArrayList<>();
                        for (Integer inte : listIdFilm) {
                            System.out.println(inte);
                            Film film = daoFilm.getFilm(con, inte);
                            ArrayList<Schedule> listScheduleForFilm = daoSchedule.getListScheduleByFilmDate(con, film, date);
                            Map<Film, ArrayList<Schedule>> alt = new HashMap<>();
                            alt.put(film, listScheduleForFilm);
                            filmSchedule.add(alt);
                            System.out.println("-------------------");
                        }

                        all.put(date, filmSchedule);
                        System.out.println(all.put(date, filmSchedule));
                    }
//                    System.out.println(listAll);
                    request.setAttribute("listAll", all);
                    request.setAttribute("listDate", listDate);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
                    dispatcher.forward(request, response);
                    break;
                }
                case "seat": {
                    if (session.getAttribute("user") != null) {
                        if (request.getParameter("film") == null || request.getParameter("date") == null || request.getParameter("time") == null) {
                            response.sendRedirect("home?controller=schedule");
                        } else {
                            try {
                                DateFormat formatter = new SimpleDateFormat("HH:mm");
                                Time time = new Time(formatter.parse(request.getParameter("time")).getTime());
                                SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
                                Date date = new Date(formatterDate.parse(request.getParameter("date")).getTime());
                                Film film = daoFilm.getFilm(con, Integer.parseInt(request.getParameter("film")));

                                Schedule sch = daoSchedule.getSchedule(con, film, date, time);
                                int numberSeated = daoSchedule.getNumberSeated(con, sch.getRoom().getId());
                                int numberSeat = daoSchedule.getNumberSeat(con, sch.getRoom().getId());
                                request.setAttribute("schedule", sch);
                                request.setAttribute("numberSeated", numberSeated);
                                request.setAttribute("numberSeat", numberSeat);
                                System.out.println(sch);
                            } catch (ParseException ex) {
                                Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
                            dispatcher.forward(request, response);
                        }
                    } else {
                        response.sendRedirect("home?controller=login_signin");

                    }
                    System.out.println("OKLAH");
                    break;
                }
                case "pre_show": {
                    request.setAttribute("listPreFilm", daoFilm.getListPreFilm(con));
                    RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
                    dispatcher.forward(request, response);
                    System.out.println("pre film");
                    break;
                }
                case "order_view": {
                    ArrayList<Order> listSS = new ArrayList<Order>();
                    ArrayList<Order> list = new ArrayList<Order>();
                    Client client = (Client) session.getAttribute("user");
                    listSS = daoOrder.getOrderSucess(con, client);
                    request.setAttribute("sucess", listSS);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
                    dispatcher.forward(request, response);
                    break;
                }
                case "order_buy": {
                    int id = Integer.parseInt(request.getParameter("schedule"));
                    int id_order = Integer.parseInt(request.getParameter("order"));
                    String[] type = {"A", "B", "C", "D", "E", "F", "G", "H"};
                    int j = 1;
                    String value1 = "";
                    Client client = (Client) session.getAttribute("user");
                    Schedule schedule = daoSchedule.getSchedule(con, id);
                    Order order = daoOrder.getOrder(con, id_order);
                    System.out.println(client.getId());
                    ArrayList<Seat> se = daoSchedule.getListTicket(con, schedule, client.getId(), order.getId());
                    for (Seat seat : se) {
                        if (seat.getRow() == j) {
                            value1 += type[seat.getRow() - 1] + seat.getCol() + ",";
                        } else {
                            j = seat.getRow();
                            value1 += type[seat.getRow() - 1] + seat.getCol() + ",";
                        }
                    }
                    request.setAttribute("seat", value1);
                    request.setAttribute("schedule", schedule);
                    request.setAttribute("order", order);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
                    dispatcher.forward(request, response);
                    break;
                }
                default: {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
                    dispatcher.forward(request, response);
                    break;
                }
            }
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
            dispatcher.forward(request, response);
        }
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

    public ArrayList<Date> getListDate() {
        Date today = new Date(System.currentTimeMillis());
        ArrayList<Date> list = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        list.add(today);
        for (int i = 1; i < 7; i++) {
            c.add(Calendar.DATE, 1);
            Date currentDatePlusOne = new Date(c.getTimeInMillis());
            list.add(currentDatePlusOne);
//            SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
//            System.out.println(formatterDate.format(currentDatePlusOne));
        }
        System.out.println(list);
        return list;
    }
}
