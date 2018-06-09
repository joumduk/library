/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author peter
 */
@Entity
@Table(name = "rent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rent.findAll", query = "SELECT r FROM Rent r")
    , @NamedQuery(name = "Rent.findAll2", query = "SELECT r,s FROM Rent r,Student s WHERE s.id=r.idStudent")
    , @NamedQuery(name = "Rent.findById", query = "SELECT r FROM Rent r WHERE r.id = :id")
    , @NamedQuery(name = "Rent.findByIdStudent", query = "SELECT r FROM Rent r WHERE r.idStudent = :idStudent")
    , @NamedQuery(name = "Rent.findByRentDate", query = "SELECT r FROM Rent r WHERE r.rentDate = :rentDate")
    , @NamedQuery(name = "Rent.findByExpireDate", query = "SELECT r FROM Rent r WHERE r.expireDate = :expireDate")
    , @NamedQuery(name = "Rent.findByStatus", query = "SELECT r FROM Rent r WHERE r.status = :status")})
public class Rent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_student")
    private int idStudent;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rent_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rentDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expire_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private boolean status;

    public Rent() {
    }

    public Rent(Integer id) {
        this.id = id;
    }

    public Rent(Integer id, int idStudent, Date rentDate, Date expireDate, boolean status) {
        this.id = id;
        this.idStudent = idStudent;
        this.rentDate = rentDate;
        this.expireDate = expireDate;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rent)) {
            return false;
        }
        Rent other = (Rent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controller.Rent[ id=" + id + " ]";
    }
    
}
