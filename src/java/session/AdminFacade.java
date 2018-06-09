/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Admin;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author peter
 */
@Stateless
public class AdminFacade extends AbstractFacade<Admin> {

    @PersistenceContext(unitName = "libraryPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdminFacade() {
        super(Admin.class);
    }
    public  Admin findByAdmin(String username) {
        return (Admin) em.createNamedQuery("Admin.findByUsername").setParameter("username", username).getSingleResult();
    }
    public  Admin login(String username,String password) {
        try{
        Admin admin =(Admin) em.createNamedQuery("Admin.login").setParameter("username", username).setParameter("password", password).getSingleResult();
        return admin;
        }catch(Exception x){
            return new Admin(-1);
        }
    }
}
