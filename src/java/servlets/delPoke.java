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
 * @author xaviv
 */
public class delPoke extends HttpServlet {

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
            String poke_name = request.getParameter("poke");
            String trainer_name = request.getParameter("trainer");

                  
            if (miEjb.pokeExist(poke_name)) {
                  if (miEjb.delPoke(poke_name, trainer_name)) {
                      out.println("<h3>El pokemon " + poke_name + " del entrenador " + trainer_name + " ha sido eliminado correctamente</h3>");
                      out.println("<form action=\"index.html\"><button type=\"submit\">Ir a home</button></form>");
                      out.println("<form action=\"viewPoke\"><input type=\"hidden\" name=\"nombre\" value="+trainer_name+"><button type=\"submit\">Seguir viendo pokemon de "+trainer_name+"</button></form>");
                  } else {
                      out.println("<h3>No se pudo eliminar el "+poke_name+"!</h3>");
                  }
              } else {
                  out.println("<h3>Ya existe este Pokemon de " + trainer_name + ".</h3>");
                  out.println("<form action=\"index.html\"><button type=\"submit\">Ir a home</button></form>");
                  out.println("<form action=\"viewPoke\"><input type=\"hidden\" name=\"nombre\" value="+trainer_name+"><button type=\"submit\">Seguir viendo pokemon de "+trainer_name+"</button></form>");
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
