/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.StukemonEJB;
import entities.Pokemon;
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
public class pokeList extends HttpServlet {

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
                List<Pokemon> pokeList = miEjb.getAllPokemons();
                out.println("<h3>Hay un total de "+pokeList.size()+" pokemons en la base de datos</h3>");
                out.println("<form action=\"index.html\"><button type=\"submit\">Ir a home</button></form>");
                out.println("<table style=\"width:100%;text-align:center\">");
                out.println("<tr>");
                out.println("<th>Nombre</th>");
                out.println("<th>Tipo</th>");
                out.println("<th>Habilidad</th>");
                out.println("<th>Nivel de ataque</th>");
                out.println("<th>Nivel de defensa</th>");
                out.println("<th>Velocidad</th>");
                out.println("<th>Vida</th>");
                out.println("<th>Nivel</th>");
                out.println("<th>Entrenador</th>");
                out.println("</tr>");
                for (Pokemon poke : pokeList) {
                    out.println("<tr>");
                    out.println("<td>" + poke.getName() + "</td>");
                    out.println("<td>" + poke.getType() + "</td>");
                    out.println("<td>" + poke.getAbility() + "</td>");
                    out.println("<td>" + poke.getAttack() + "</td>");
                    out.println("<td>" + poke.getDefense() + "</td>");
                    out.println("<td>" + poke.getSpeed() + "</td>");
                    out.println("<td>" + poke.getLife() + "</td>");
                    out.println("<td>" + poke.getLevel() + "</td>");
                    out.println("<td>" + poke.getTrainer().getName() + "</td>");
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
