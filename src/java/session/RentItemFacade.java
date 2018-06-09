/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.RentItem;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author peter
 */
@Stateless
public class RentItemFacade extends AbstractFacade<RentItem> {

    @PersistenceContext(unitName = "libraryPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RentItemFacade() {
        super(RentItem.class);
    }
    public List<RentItem> findByIdRent(int id){
        return em.createNamedQuery("RentItem.findByIdRent").setParameter("idRent",id).getResultList();
    }
    
}
