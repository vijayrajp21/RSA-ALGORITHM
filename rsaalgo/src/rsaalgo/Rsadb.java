/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsaalgo;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author lenovo
 */
@Entity
@Table(name = "RSADB")
@NamedQueries({
    @NamedQuery(name = "Rsadb.findAll", query = "SELECT r FROM Rsadb r"),
    @NamedQuery(name = "Rsadb.findByPerson", query = "SELECT r FROM Rsadb r WHERE r.person = :person"),
    @NamedQuery(name = "Rsadb.findByPrimep", query = "SELECT r FROM Rsadb r WHERE r.primep = :primep"),
    @NamedQuery(name = "Rsadb.findByPrimeq", query = "SELECT r FROM Rsadb r WHERE r.primeq = :primeq"),
    @NamedQuery(name = "Rsadb.findByPublicn", query = "SELECT r FROM Rsadb r WHERE r.publicn = :publicn"),
    @NamedQuery(name = "Rsadb.findByPublice", query = "SELECT r FROM Rsadb r WHERE r.publice = :publice"),
    @NamedQuery(name = "Rsadb.findByPrivated", query = "SELECT r FROM Rsadb r WHERE r.privated = :privated")})
public class Rsadb implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PERSON")
    private String person;
    @Column(name = "PRIMEP")
    private BigInteger primep;
    @Column(name = "PRIMEQ")
    private BigInteger primeq;
    @Column(name = "PUBLICN")
    private BigInteger publicn;
    @Column(name = "PUBLICE")
    private BigInteger publice;
    @Column(name = "PRIVATED")
    private BigInteger privated;

    public Rsadb() {
    }

    public Rsadb(String person) {
        this.person = person;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        String oldPerson = this.person;
        this.person = person;
        changeSupport.firePropertyChange("person", oldPerson, person);
    }

    public BigInteger getPrimep() {
        return primep;
    }

    public void setPrimep(BigInteger primep) {
        BigInteger oldPrimep = this.primep;
        this.primep = primep;
        changeSupport.firePropertyChange("primep", oldPrimep, primep);
    }

    public BigInteger getPrimeq() {
        return primeq;
    }

    public void setPrimeq(BigInteger primeq) {
        BigInteger oldPrimeq = this.primeq;
        this.primeq = primeq;
        changeSupport.firePropertyChange("primeq", oldPrimeq, primeq);
    }

    public BigInteger getPublicn() {
        return publicn;
    }

    public void setPublicn(BigInteger publicn) {
        BigInteger oldPublicn = this.publicn;
        this.publicn = publicn;
        changeSupport.firePropertyChange("publicn", oldPublicn, publicn);
    }

    public BigInteger getPublice() {
        return publice;
    }

    public void setPublice(BigInteger publice) {
        BigInteger oldPublice = this.publice;
        this.publice = publice;
        changeSupport.firePropertyChange("publice", oldPublice, publice);
    }

    public BigInteger getPrivated() {
        return privated;
    }

    public void setPrivated(BigInteger privated) {
        BigInteger oldPrivated = this.privated;
        this.privated = privated;
        changeSupport.firePropertyChange("privated", oldPrivated, privated);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (person != null ? person.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rsadb)) {
            return false;
        }
        Rsadb other = (Rsadb) object;
        if ((this.person == null && other.person != null) || (this.person != null && !this.person.equals(other.person))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rsaalgo.Rsadb[ person=" + person + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
