/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.StukemonEJB;
import entities.Pokemon;
import entities.Trainer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author xaviv
 */
public class trainerList extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            String name = request.getParameter("nombre");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>View all pokemons</title>");
            out.println("</head>");
            out.println("<body>");
                List<Trainer> trainersList = miEjb.getAllTrainers();
                out.println("<h3>Hay un total de "+trainersList.size()+" entrenadores en la base de datos</h3>");
                out.println("<form action=\"index.html\"><button type=\"submit\">Ir a home</button></form>");
                out.println("<table style=\"width:100%;text-align:center\">");
                out.println("<tr>");
                out.println("<th>Nombre</th>");
                out.println("<th>Pokeballs</th>");
                out.println("<th>Potions</th>");
                out.println("<th>Points</th>");
                out.println("</tr>");
                for (Trainer trainer : trainersList) {
                    out.println("<tr>");
                    out.println("<td>" + trainer.getName() + "</td>");
                    out.println("<td>" + trainer.getPokeballs() + "</td>");
                    out.println("<td>" + trainer.getPotions() + "</td>");
                    out.println("<td>" + trainer.getPoints() + "</td>");
                    out.println("</tr>");
                }

                out.println("</table>");
                out.println("<body>");
                out.println("<body>");
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
