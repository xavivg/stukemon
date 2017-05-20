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
public class upPotions extends HttpServlet {

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
            String trainer_name = request.getParameter("nombre");
            int potions = Integer.parseInt(request.getParameter("potions"));
            if (miEjb.trainerExist(trainer_name)) {
                Trainer trainer = miEjb.getTrainer(trainer_name);
                if (trainer.getPoints() > potions * 10) {
                    if (miEjb.upPotions(trainer_name, potions)) {
                        out.println("<h2>La obtención de pociones a sido aceptada, ahora tienes " + trainer.getPoints() + " puntos y " + trainer.getPotions() + " pociones</h2>");
                    } else {
                        out.println("<h2>Algo falló en la operación</h2>");
                    }
                } else {
                    out.println("<h2>" + trainer.getName() + " no tiene los suficientes puntos ( tiene : " + trainer.getPoints() + " ) para comprar " + potions + " pociones, ahora tiene" + trainer.getPotions() + "</h2>");
                }
            } else {
                out.println("<h2>No se encuentra ningun trainer de nombre " + trainer_name + "</h2>");
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
