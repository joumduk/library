/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Admin;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.AdminFacade;

/**
 *
 * @author peter
 */
@WebServlet(name = "Auth",loadOnStartup = 1, urlPatterns = {"/login","/logout","/signup","/profile","/logout"})
public class Auth extends HttpServlet {

    private Admin admin;
    
    @EJB
    AdminFacade adminFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
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
        HttpSession session = request.getSession();
        admin = (Admin) session.getAttribute("admin");
        
        String userPath = request.getServletPath();
        // if category page is requested
        switch (userPath) {
            case "/login":
                // TODO: Implement category request
                if(admin!=null){
                    response.sendRedirect("home");
                }
                userPath = "/login";
                // if cart page is requested
                break;
            case "/signup":
                // TODO: Implement cart page request
                if(admin!=null){
                    response.sendRedirect("home");
                }
                userPath = "/signup";
                
                // if checkout page is requested
                break;
            case "/profile":
                // TODO: Implement checkout page request
                userPath = "/profile";
                // if user switches language
                break;
            case "/logout":
                // TODO: Implement checkout page request
                session.removeAttribute("admin");
                response.sendRedirect("login");
                // if user switches language
                break;
            default:
                
                break;
        }
        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/view/auth" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
        HttpSession session = request.getSession();
        admin = (Admin) session.getAttribute("admin");
        
        String userPath = request.getServletPath();
        // if category page is requested
        switch (userPath) {
            case "/login":
                // TODO: Implement category request
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                Admin logadmin = adminFacade.login(username,password);
                if(logadmin.getId() != -1){
                    session.setAttribute("admin", logadmin);
                    response.sendRedirect("home");
                }else{
                    request.setAttribute("errorMessage","Invalid Username and password");
                }
                userPath = "/login";
                // if cart page is requested
                break;
            case "/signup":
                // TODO: Implement cart page request
                if(admin!=null){
                    response.sendRedirect("home");
                }
                userPath = "/signup";
                
                // if checkout page is requested
                break;
            case "/profile":
                // TODO: Implement checkout page request
                userPath = "/profile";
                // if user switches language
                break;
            default:
                
                break;
        }
        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/view/auth" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
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
