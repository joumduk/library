/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Rent;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author peter
 */
@Stateless
public class RentFacade extends AbstractFacade<Rent> {

    @PersistenceContext(unitName = "libraryPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RentFacade() {
        super(Rent.class);
    }
    public List<Object> findAll2(){
        return em.createNamedQuery("Rent.findAll2").getResultList();
    }
}
