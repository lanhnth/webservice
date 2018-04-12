/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.admin;

import control.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
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

/**
 *
 * @author ducvu
 */
public class HomeAdminServlet extends HttpServlet {

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
            out.println("<title>Servlet HomeAdminServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeAdminServlet at " + request.getContextPath() + "</h1>");
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
        if (request.getParameter("controller") != null){
            String controller = request.getParameter("controller");
            //schedule 
            switch(controller){
                case "schedule":{
                    request.setAttribute("listFilm", daoFilm.getListFilm(con));
                    request.setAttribute("listSchedule", daoSchedule.getListSchedule(con));
                    request.setAttribute("listSale", daoSale.getListSale(con));
                    request.setAttribute("listRoom", daoRoom.getListInforRoom(con));
                    break;
                } 
                case "room":{
                    request.setAttribute("listRoom", daoRoom.getListRoom(con));
                    request.setAttribute("listCinema", daoCinema.getListCinema(con));
                    break;
                } 
                case "seat":{
                    request.setAttribute("listRoom", daoRoom.getListRoom(con));
                    request.setAttribute("listSeat", daoSeat.getListSeat(con));
                    break;
                }
                case "sale":{
                    request.setAttribute("listSale", daoSale.getListSale(con));
                    break;
                }
                case "film":{
                    request.setAttribute("listCategory", daoCate.getListCategory(con));
                    request.setAttribute("listFilm", daoFilm.getListFilm(con));
                    break;
                }
                case "category":{
                    request.setAttribute("listCategory", daoCate.getListCategory(con));
                    break;
                }
                default:{
                    
                }
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/index.jsp");
        dispatcher.forward(request, response);
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
        HttpSession session = request.getSession();
        System.out.println(encryption("admin123"));
        String username = request.getParameter("username");
        String password = encryption(request.getParameter("password"));
        System.out.println(password);
        User user = new User(0, "", username, password, 0);
        
        if(userDao.checkLoginAdmin(con, user)){
            SaleDAO daoSale = new SaleDAOImpl();
            FilmDAO daoFilm = new FilmDAOImpl();
            session.setAttribute("admin_login", user);
            response.sendRedirect("admin?controller=home");
        }else {
            response.sendRedirect("admin");
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

    public static String encryption(String str) {
       byte[] defaultBytes = str.getBytes();
       try {
           MessageDigest algorithm = MessageDigest.getInstance("MD5");
           algorithm.reset();
           algorithm.update(defaultBytes);
           byte messageDigest[] = algorithm.digest();
           StringBuffer hexString = new StringBuffer();
           for (int i = 0; i < messageDigest.length; i++) {
              String hex = Integer.toHexString(0xFF & messageDigest[i]);
              if (hex.length() == 1) {
                  hexString.append('0');
              }
              hexString.append(hex);
          }
          str = hexString + "";
       } catch (NoSuchAlgorithmException e) {
          e.printStackTrace();
       }
       return str;
    }
}
