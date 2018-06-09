/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Admin;
import entity.Student;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.StudentFacade;

/**
 *
 * @author peter
 */
@WebServlet(name = "StudentController", urlPatterns = {"/student", "/studentdetail", "/newstudent", "/updatestudent", "/removestudent"})
public class StudentController extends HttpServlet {
    private Admin admin;
    @EJB
    StudentFacade studentFacade;
    Student student;
    String studentId;
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
        HttpSession session = request.getSession();
        admin = (Admin) session.getAttribute("admin");
        
        String userPath = request.getServletPath();
        // if category page is requested
        switch (userPath) {
            case "/student":
                // TODO: Implement category request
                if(admin==null){
                    response.sendRedirect("login");
                }
                try{
                    request.setAttribute("students", studentFacade.findAll());  
                }catch(Exception e){
                }
                
                userPath = "/list";
                // if cart page is requested
                break;
            case "/newstudent":
                // TODO: Implement cart page request
                if(admin==null){
                    response.sendRedirect("login");
                }
                userPath = "/newstudent";
                
                // if checkout page is requested
                break;
            case "/studentdetail":
                // TODO: Implement checkout page request
                studentId= request.getQueryString();
                // get customer details
                student = studentFacade.find(Integer.parseInt(studentId));
                request.setAttribute("student", student);
                userPath = "/detail";
                // if user switches language
                break;
            case "/updatestudent":
                // TODO: Implement checkout page request
                studentId = request.getQueryString();
                student = studentFacade.find(Integer.parseInt(studentId));
                request.setAttribute("student", student);
                userPath = "/update";
                // if user switches language
                break;
            case "/removestudent":
                // TODO: Implement checkout page request
                studentId = request.getQueryString();
                student = studentFacade.find(Integer.parseInt(studentId));
                studentFacade.remove(student);
                response.sendRedirect("student");
                // if user switches language
                break;
                
            default:
                
                break;
        }
        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/view/student" + userPath + ".jsp";

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
        HttpSession session = request.getSession();
        admin = (Admin) session.getAttribute("admin");
        String student_id ;
        String name;
        String phone;
        String email;
        String userPath = request.getServletPath();
        // if category page is requested
        switch (userPath) {
            case "/student":
                // TODO: Implement category request
                if(admin==null){
                    response.sendRedirect("login");
                }
                try{
                    request.setAttribute("students", studentFacade.findAll());  
                }catch(Exception e){
                }
                
                userPath = "/list";
                // if cart page is requested
                break;
            case "/newstudent":
                // TODO: Implement cart page request
                if(admin==null){
                    response.sendRedirect("login");
                }
                 student_id = request.getParameter("student_id");
                 name = request.getParameter("name");
                 phone = request.getParameter("phone");
                 email = request.getParameter("email");
                if(student_id != "" && name != "" && phone != "" && email!=""){
                    Student newstudent=new Student();
                    newstudent.setStudentId(student_id);
                    newstudent.setName(name);
                    newstudent.setPhone(phone);
                    newstudent.setEmail(email);
                    newstudent.setId(-1);
                    studentFacade.create(newstudent);
                    
                    response.sendRedirect("student");
                }
                userPath = "/newstudent";
                
                // if checkout page is requested
                break;
            case "/studentdetail":
                // TODO: Implement checkout page request
                userPath = "/detail";
                // if user switches language
                break;
            case "/updatestudent":
                // TODO: Implement checkout page request
                studentId = request.getQueryString();
                student = studentFacade.find(Integer.parseInt(studentId));
                 student_id = request.getParameter("student_id");
                 name = request.getParameter("name");
                 phone = request.getParameter("phone");
                 email = request.getParameter("email");
                student.setStudentId(student_id);
                student.setName(name);
                student.setPhone(phone);
                student.setEmail(email);
                studentFacade.edit(student);
                
                response.sendRedirect("student");
                // if user switches language
                // if user switches language
                break;
            default:
                
                break;
        }
        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/view/student" + userPath + ".jsp";

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
