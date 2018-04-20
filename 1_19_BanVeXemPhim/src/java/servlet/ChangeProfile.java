/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Client;
import modelDAO.ClientDAO;
import modelDAO.ClientDAOImpl;

/**
 *
 * @author nguye
 */
public class ChangeProfile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("pro_id"));
        String name = request.getParameter("pro_name");
        String address = request.getParameter("pro_address");
        String phone = request.getParameter("pro_phone");
        System.out.println(name+" "+address+" "+phone+" "+id);
        Client d = new Client();
        d.setName(name);
        d.setAddress(address);
        d.setPhone(phone);
        d.setId(id);
        ClientDAO x = new ClientDAOImpl();
        x.changeProfile(d);
        response.sendRedirect("home?controller=member_account");
    }
}
