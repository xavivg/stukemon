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
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author xaviv
 */
public class upLife extends HttpServlet {

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
            if (miEjb.trainerExist(trainer_name)) {
            Trainer trainer = miEjb.getTrainer(trainer_name);
            Pokemon poke = miEjb.getPoke(poke_name);
            if(trainer.getPotions() > 1){
                if(miEjb.upLife(trainer_name, poke_name)){
                    out.println("<h3>El pokemon "+poke_name+" de "+trainer_name+" ha recibido 50 de vida, ahora tiene "+poke.getLife()+"</h3>");
                    out.println("<h3>El entrenador "+trainer_name+" ha gastado una pocion, le quedan "+trainer.getPotions()+"</h3>");
                }
            }
            }
            else{
                out.println("<h3>No existe ningun entrenador con el nombre " + trainer_name + "</h3>");
                out.println("<form action=\"newTrainerForm.html\"><button type=\"submit\">Crear a " + trainer_name + "</button></form>");
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
