/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author peter
 */
@Entity
@Table(name = "rent_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RentItem.findAll", query = "SELECT r FROM RentItem r")
    , @NamedQuery(name = "RentItem.findByIdRentItem", query = "SELECT r FROM RentItem r WHERE r.idRentItem = :idRentItem")
    , @NamedQuery(name = "RentItem.findByIdRent", query = "SELECT r FROM RentItem r WHERE r.idRent = :idRent")
    , @NamedQuery(name = "RentItem.findByIdBook", query = "SELECT r FROM RentItem r WHERE r.idBook = :idBook")
    , @NamedQuery(name = "RentItem.findByQuantity", query = "SELECT r FROM RentItem r WHERE r.quantity = :quantity")
    , @NamedQuery(name = "RentItem.findByBookName", query = "SELECT r FROM RentItem r WHERE r.bookName = :bookName")
    , @NamedQuery(name = "RentItem.findByStatus", query = "SELECT r FROM RentItem r WHERE r.status = :status")})
public class RentItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_rent_item")
    private Integer idRentItem;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_rent")
    private int idRent;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_book")
    private int idBook;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private int quantity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "book_name")
    private String bookName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private boolean status;

    public RentItem() {
    }

    public RentItem(Integer idRentItem) {
        this.idRentItem = idRentItem;
    }

    public RentItem(Integer idRentItem, int idRent, int idBook, int quantity, String bookName, boolean status) {
        this.idRentItem = idRentItem;
        this.idRent = idRent;
        this.idBook = idBook;
        this.quantity = quantity;
        this.bookName = bookName;
        this.status = status;
    }

    public Integer getIdRentItem() {
        return idRentItem;
    }

    public void setIdRentItem(Integer idRentItem) {
        this.idRentItem = idRentItem;
    }

    public int getIdRent() {
        return idRent;
    }

    public void setIdRent(int idRent) {
        this.idRent = idRent;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRentItem != null ? idRentItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RentItem)) {
            return false;
        }
        RentItem other = (RentItem) object;
        if ((this.idRentItem == null && other.idRentItem != null) || (this.idRentItem != null && !this.idRentItem.equals(other.idRentItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RentItem[ idRentItem=" + idRentItem + " ]";
    }
    
}
