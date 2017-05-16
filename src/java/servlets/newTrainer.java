/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.StukemonEJB;
import entities.Trainer;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author usu26
 */
public class newTrainer extends HttpServlet {

    @EJB
    StukemonEJB miEjb;

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
            String name = request.getParameter("nombre");
            int pokeballs = Integer.parseInt(request.getParameter("pokeballs"));
            int potions = Integer.parseInt(request.getParameter("potions"));
            Trainer c = new Trainer(name, pokeballs, potions, 0);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet newTrainer</title>");
            out.println("</head>");
            out.println("<body>");
            if (!miEjb.trainerExist(name)) {
                if (miEjb.createTrainer(c)) {
                    out.println("<h3>" + name + " fué creado correctamente</h3>");
                    out.println("<form action=\"index.html\"><button type=\"submit\">Ir a home</button></form>");
                } else {
                    out.println("<h3>Hubo un error creando el entrenador</h3>");
                }
                out.println("<h1>Servlet newTrainer at " + request.getContextPath() + "</h1>");
                out.println("</body>");
                out.println("</html>");
            }
            else{
                 out.println("<h3>Ya esta creado este Trainer</h3>");
                 out.println("<form action=\"index.html\"><button type=\"submit\">Ir a home</button></form>");
            }
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
