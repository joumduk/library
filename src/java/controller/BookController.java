/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Admin;
import entity.Book;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.BookFacade;

/**
 *
 * @author peter
 */
@WebServlet(name = "BookController", urlPatterns = {"/book", "/bookdetail", "/booksearch"})
public class BookController extends HttpServlet {
    Admin admin;
    @EJB
    BookFacade bookFacade;
    Book book;
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

    }

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
        HttpSession session = request.getSession();
        admin = (Admin) session.getAttribute("admin");
        String userPath = request.getServletPath();
        String bookId;
        // if category page is requested
        switch (userPath) {
            case "/book":
                // TODO: Implement category request
                if(admin==null){
                    response.sendRedirect("login");
                }
                try{
                    request.setAttribute("books", bookFacade.findAll());  
                }catch(Exception e){
                }
                
                userPath = "/list";
                // if cart page is requested
                break;
            case "/bookdetail":
                // TODO: Implement checkout page request
                bookId= request.getQueryString();
                // get customer details
                book = bookFacade.find(Integer.parseInt(bookId));
                request.setAttribute("book", book);  
                userPath = "/detail";
                // if user switches language
                break;
            case "/booksearch":
                // TODO: Implement checkout page request
                String keyword = request.getParameter("keyword");
                System.out.print(keyword);
                if(keyword!="" && keyword != null){
                    request.setAttribute("keyword", keyword); 
                    request.setAttribute("books", bookFacade.search(keyword)); 
                }
                
                userPath = "/search";
                // if user switches language
                // if user switches language
                break;
            default:
                
                break;
        }
        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/view/book" + userPath + ".jsp";

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
