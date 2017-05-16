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
 * @author usu26
 */
public class newPoke extends HttpServlet {

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
            String type = request.getParameter("type");
            String ability = request.getParameter("habilidad");
            int attackLevel = Integer.parseInt(request.getParameter("nivelAtaque"));
            int defenseLevel = Integer.parseInt(request.getParameter("nivelDefensa"));
            int speed = Integer.parseInt(request.getParameter("velocidad"));
            int life = Integer.parseInt(request.getParameter("vida"));

            String trainer_name = request.getParameter("entrenador");

            Pokemon poke = new Pokemon(name, type, ability, attackLevel, defenseLevel, speed, life, 0);

            if (!miEjb.trainerExist(trainer_name)) {
                out.println("<h3>El entrenador " + trainer_name + " no existe! Por favor introduce el nombre de uno valido</h3>");
                out.println("<form action=\"index.html\"><button type=\"submit\">Ir a home</button></form>");
                out.println("<form action=\"newTrainerForm.html\"><button type=\"submit\">Crear a " + trainer_name + "</button></form>");
            } else {
                //Obtener objeto trainer, añadir trainer al obj poke y luego insertar pokemon a la base de datos
                //Comrpobar que el trainer no tiene más de 6 pokemons
                Trainer trainer = miEjb.getTrainer(trainer_name);
                if (miEjb.countPoke(trainer)) {
                    poke.setTrainer(trainer);
                    if (!miEjb.pokeExist(poke.getName())) {
                        if (miEjb.createPoke(poke)) {
                            out.println("<h3>El pokemon " + name + " del entrenador " + trainer_name + " fué creado correctamente</h3>");
                            out.println("<form action=\"index.html\"><button type=\"submit\">Ir a home</button></form>");
                        } else {
                            out.println("<h3>El pokemon " + name + " del entrenador " + trainer_name + " ya existe!</h3>");
                        }
                    } else {
                        out.println("<h3>Ya existe este Pokemon de " + trainer_name + ".</h3>");
                        out.println("<form action=\"index.html\"><button type=\"submit\">Ir a home</button></form>");
                    }
                } else {
                    out.println("<h3>El entrenador " + trainer_name + " ya tiene el máximo de Pokemons posibles(6), elimina 1 e intentalo de nuevo.</h3>");
                    out.println("<form action=\"index.html\"><button type=\"submit\">Ir a home</button></form>");
                    out.println("<form action=\"index.html\"><button type=\"submit\">Borrar un Pokemon</button></form>"); //TODO Pending to change action
                }

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
