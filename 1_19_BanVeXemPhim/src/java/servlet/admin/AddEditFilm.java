/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.admin;

import control.DBConnection;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Category;
import model.Film;
import modelDAO.FilmDAO;
import modelDAO.FilmDAOImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author NguyenNgoc
 */
public class AddEditFilm extends HttpServlet {

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
            out.println("<title>Servlet AddEditFilm</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddEditFilm at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        ArrayList<Category> listCategory = (ArrayList<Category>) session.getAttribute("listCategory");
        System.out.println(listCategory.size());
        if (request.getParameter("action") != null) {
            String action = request.getParameter("action");
            switch (action) {
                case "add": {
                    System.out.println("running");
                    FileItemFactory itemfactory = new DiskFileItemFactory();
                    ServletFileUpload upload = new ServletFileUpload(itemfactory);

                    try {

                        List<FileItem> items = upload.parseRequest(request);
                        System.out.println("File item");
                        Film film = new Film();
                        Hashtable params = new Hashtable();
                        String fileName = "";
                        for (FileItem item : items) {
                            if (!item.isFormField()) {
                                String itemName = item.getName();
                                fileName = itemName.substring(itemName.lastIndexOf("\\") + 1);
                                System.out.println("path: " + fileName);
                                String realPath = getServletContext().getRealPath("/") + "Image\\" + fileName;
                                System.out.println("Rpath: " + realPath);
                                File saveFile = new File(realPath);
                                item.write(saveFile);
                                film.setPoster(fileName);
                            } else {
                                params.put(item.getFieldName(), item.getString());
                            }
                        }
                        String nameFilm = (new String(((String) params.get("name")).getBytes("iso-8859-1"), "UTF-8")).toString();
                        System.out.println(nameFilm);
                        String country = (new String(((String) params.get("country")).getBytes("iso-8859-1"), "UTF-8")).toString();
                        String des = (new String(((String) params.get("des")).getBytes("iso-8859-1"), "UTF-8")).toString();
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        java.sql.Date sdate = new java.sql.Date(formatter.parse((new String(((String) params.get("releaseDate")).getBytes("iso-8859-1"), "UTF-8")).toString()).getTime());

                        float vote = Float.parseFloat((new String(((String) params.get("vote")).getBytes("iso-8859-1"), "UTF-8")).toString());
                        int time = Integer.parseInt((new String(((String) params.get("runningTime")).getBytes("iso-8859-1"), "UTF-8")).toString());
                        String trailer = (new String(((String) params.get("trailer")).getBytes("iso-8859-1"), "UTF-8")).toString();

                        String category = (new String(((String) params.get("category")).getBytes("iso-8859-1"), "UTF-8")).toString();
                        for (Category c : listCategory) {
                            if (c.getName().equals(category)) {
                                film.addCategory(c);
                                break;
                            }
                        }
                        film.setName(nameFilm);
                        film.setProductCountry(country);
                        film.setDes(des);
                        film.setRealeaseDate(sdate);
                        film.setTrailer(trailer);
                        film.setVote(vote);
                        film.setRunningTime(time);
                        Connection con = DBConnection.getConnection();
                        FilmDAO dao = new FilmDAOImpl();
                        if (dao.addFilm(con, film)) {
                            request.setAttribute("listFilm", dao.getListFilm(con));
                            response.sendRedirect("admin?controller=film");
                        } else {
                            response.sendRedirect("admin");
                        }
                    } catch (FileUploadException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "edit": {
                    FileItemFactory itemfactory = new DiskFileItemFactory();
                    ServletFileUpload upload = new ServletFileUpload(itemfactory);

                    try {

                        List<FileItem> items = upload.parseRequest(request);
                        System.out.println("File item");
                        Film film = new Film();
                        Hashtable params = new Hashtable();
                        String fileName = "";
                        for (FileItem item : items) {
                            if (!item.isFormField()) {
                                String itemName = item.getName();
                                fileName = itemName.substring(itemName.lastIndexOf("\\") + 1);
                                System.out.println("path: " + fileName);
                                String realPath = getServletContext().getRealPath("/") + "Image\\" + fileName;
                                System.out.println("Rpath: " + realPath);
                                File saveFile = new File(realPath);
                                item.write(saveFile);
                                film.setPoster(fileName);
                            } else {
                                params.put(item.getFieldName(), item.getString());
                            }
                        }
                        int id = Integer.parseInt((new String(((String) params.get("idEdit")).getBytes("iso-8859-1"), "UTF-8")).toString());
                        System.out.println(id);
                        String nameFilm = (new String(((String) params.get("nameEdit")).getBytes("iso-8859-1"), "UTF-8")).toString();
                        System.out.println(nameFilm);
                        String country = (new String(((String) params.get("countryEdit")).getBytes("iso-8859-1"), "UTF-8")).toString();
                        String des = (new String(((String) params.get("desEdit")).getBytes("iso-8859-1"), "UTF-8")).toString();
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            System.out.println(formatter.parse(request.getParameter("releaseDate")));
                        java.sql.Date sdate = new java.sql.Date(formatter.parse((new String(((String) params.get("releaseDateEdit")).getBytes("iso-8859-1"), "UTF-8")).toString()).getTime());

                        float vote = Float.parseFloat((new String(((String) params.get("voteEdit")).getBytes("iso-8859-1"), "UTF-8")).toString());
                        int time = Integer.parseInt((new String(((String) params.get("runningTimeEdit")).getBytes("iso-8859-1"), "UTF-8")).toString());
                        String trailer = (new String(((String) params.get("trailerEdit")).getBytes("iso-8859-1"), "UTF-8")).toString();
                        film.setName(nameFilm);
                        film.setProductCountry(country);
                        film.setDes(des);
                        film.setRealeaseDate(sdate);
                        film.setTrailer(trailer);
                        film.setVote(vote);
                        film.setRunningTime(time);
                        film.setId(id);
                        Connection con = DBConnection.getConnection();
                        FilmDAO dao = new FilmDAOImpl();
                        if (dao.updateFilm(con, film)) {
                            request.setAttribute("listFilm", dao.getListFilm(con));
                            response.sendRedirect("admin?controller=film");
                        } else {
                            response.sendRedirect("admin");
                        }
//            
                    } catch (FileUploadException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
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
