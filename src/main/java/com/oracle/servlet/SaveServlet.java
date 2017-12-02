/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.oracle.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import main.java.com.oracle.beans.MobileDevice;

/**
 *
 * @author ooransa
 */
@WebServlet(name = "SaveServlet", urlPatterns = {"/SaveServlet"})
public class SaveServlet extends HttpServlet {

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
        try {
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String save = request.getParameter("save");
            double totalPrice = 0;
            if (request.getSession().getAttribute("CART") == null) {
                System.out.print("Empty!");
            } else {
                List devices = (List<MobileDevice>) request.getSession().getAttribute("CART");
                if (devices.isEmpty()) {
                    System.out.print("Empty!");
                }

                for (int i = 0; i < devices.size(); i++) {
                    totalPrice += ((MobileDevice) devices.get(i)).getPrice();
                }
            }
            System.out.println("Will save Name:" + name + ", Address:" + address + ", Total Price:" + totalPrice);
            if (save != null && "1".equals(save)) {
                request.getRequestDispatcher("confirm.jsp").forward(request, response);
            } else {
                Context ctx = new InitialContext();
                DataSource ds = (DataSource) ctx.lookup("jdbc/myCustomerDS");
                Connection conn = ds.getConnection();
                Statement statement = conn.createStatement();
                int results = statement.executeUpdate("INSERT INTO ORDER_DATA " + "VALUES ('" + name + "','" + address + "'," + totalPrice + ",sysdate)");
                //close DB connection
                conn.commit();
                conn.close();
                if (results > 0) {
                    request.getRequestDispatcher("confirm.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
            request.getSession().setAttribute("ERROR", ex);
            request.getRequestDispatcher("error.jsp").forward(request, response);
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
