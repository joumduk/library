/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Admin;
import entity.Book;
import entity.Rent;
import entity.RentItem;
import entity.Student;
import info.rentinfo;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.RentFacade;
import java.util.Date;
import java.util.List;
import session.BookFacade;
import session.RentItemFacade;
import session.StudentFacade;

/**
 *
 * @author peter
 */
@WebServlet(name = "RentController", urlPatterns = {"/rent", "/rentdetail", "/return","/newrent","/additem","/removeitem","/savenewrent"})
public class RentController extends HttpServlet {
    private Admin admin;
    @EJB
    RentFacade rentFacade;
    @EJB
    BookFacade bookFacade;
    @EJB
    StudentFacade studentFacade;
    @EJB
    RentItemFacade rentItemFacade;
    
    Rent rent;
    List<rentinfo> rentinfos=new ArrayList<rentinfo>();
    private List<RentItem> item ;
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
        if (userPath.equals("/rent")){
            if(admin==null){
                response.sendRedirect("login");
            }
            rentinfos=new ArrayList<rentinfo>();
            List<Rent> rents =rentFacade.findAll();
            for(Rent r:rents){
                rentinfo info=new rentinfo();
                info.setRent(r);
                Student s=studentFacade.find(r.getIdStudent());
                info.setStudent(s);
                rentinfos.add(info);
            }
            request.setAttribute("infos", rentinfos);
            userPath = "/list";
        }else if(userPath.equals("/newrent")){
            if(admin==null){
                response.sendRedirect("login");
            }
            if(session.getAttribute("rent") == null){
                rent = new Rent();
                LocalDate date = LocalDate.now();
                LocalDate date2 = date.plusDays(7);
                rent.setRentDate(new Date(date.getYear(),date.getMonthValue(),date.getDayOfMonth()));
                rent.setExpireDate(new Date(date2.getYear(),date2.getMonthValue(),date2.getDayOfMonth()));
                rent.setStatus(false);
                session.setAttribute("rent",rent);
            }
            rent = (Rent) session.getAttribute("rent");
            request.setAttribute("rent", rent);
            item = (List<RentItem>) session.getAttribute("rentItem");
            request.setAttribute("students", studentFacade.findAll()); 
            request.setAttribute("items", item); 
            String keyword = request.getParameter("keyword");
            if(keyword!="" && keyword != null){
                request.setAttribute("keyword", keyword); 
                request.setAttribute("books", bookFacade.search(keyword)); 
            }
            userPath = "/newrent";
        }else if(userPath.equals("/additem")){
            String bookId = request.getQueryString();
                Boolean b=true;
                item = (List<RentItem>) session.getAttribute("rentItem");
                if(item ==null){
                    item = new ArrayList<RentItem>();
                }else{
                    if(item.size() > 0){
                        for (RentItem ri2 : item ){
                            if (ri2.getIdBook() == Integer.parseInt(bookId)) {
                                b=false;
                                ri2.setQuantity(ri2.getQuantity()+1);
                            }
                        }
                    }
                    
                }
                if(b){
                    RentItem ri=new RentItem();
                    Book k = bookFacade.find(Integer.parseInt(bookId));
                    ri.setIdBook(k.getId());
                    ri.setBookName(k.getTitle());
                    ri.setQuantity(1);
                    item.add(ri);
                }
                session.setAttribute("rentItem", item);
//                response.sendRedirect("newrent");  
                response.sendRedirect("newrent");
                return;

        }else if(userPath.equals("/removeitem")){
            String removeBook = request.getQueryString();
            int removeId=Integer.parseInt(removeBook);
            if(item !=null && item.size()>0){
                for (RentItem ri : item ){
                    if (ri.getIdBook() == removeId) {
                        item.remove(ri);
                        break;
                    }
                }
                session.setAttribute("rentItem", item); 
            }
            response.sendRedirect("newrent");
            return;
        }else if(userPath.equals("/rentdetail")){
            String rentid = request.getQueryString();
            rent = rentFacade.find(Integer.parseInt(rentid));
            item = rentItemFacade.findByIdRent(Integer.parseInt(rentid));
            Boolean status=true;
            for(RentItem im:item){
               if(im.getStatus() == false){
                   status=false;
               }
            }
            if(status == true){
                rent.setStatus(true);
                rentFacade.edit(rent);
            }
            Student student = studentFacade.find(rent.getIdStudent());
            request.setAttribute("rent", rent);
            request.setAttribute("items", item);
            request.setAttribute("student", student);
            userPath="/detail";
        }else if(userPath.equals("/return")){
            String rentItemid = request.getQueryString();
            RentItem it = rentItemFacade.find(Integer.parseInt(rentItemid));
            Book book = bookFacade.find(it.getIdBook());
            book.setQuantity(book.getQuantity()+it.getQuantity());
            bookFacade.edit(book);
            it.setStatus(true);
            rentItemFacade.edit(it);
            response.sendRedirect("rentdetail?"+it.getIdRent());
            return;
        }
        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/view/rent" + userPath + ".jsp";
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
//         if category page is requested
        if(userPath.equals("/savenewrent")){
            item = (List<RentItem>) session.getAttribute("rentItem");
            rent = (Rent) session.getAttribute("rent");
            if (item != null && rent !=null){
                response.sendRedirect("newrent");    
            }
            String id_student = request.getParameter("id_student");
            rent.setIdStudent(Integer.parseInt(id_student));
            rentFacade.create(rent);
            int id=rent.getId();
            
            for(RentItem ri: item){
                ri.setIdRent(id);
                rentItemFacade.create(ri);
                Book book = bookFacade.find(ri.getIdBook());
                book.setQuantity(book.getQuantity()-ri.getQuantity());
                bookFacade.edit(book);
            }
            
            try{
                session.removeAttribute("rent");
                session.removeAttribute("rentItem");
                response.sendRedirect("rent");
                userPath="/complete";
            }catch(Exception e){
                userPath="/complete";
            }
            
        }
        String url = "/WEB-INF/view/rent" + userPath + ".jsp";
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
