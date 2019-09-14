/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsaalgo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import rsaalgo.exceptions.NonexistentEntityException;
import rsaalgo.exceptions.PreexistingEntityException;

/**
 *
 * @author lenovo
 */
public class RsadbJpaController implements Serializable {

    public RsadbJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rsadb rsadb) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(rsadb);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRsadb(rsadb.getPerson()) != null) {
                throw new PreexistingEntityException("Rsadb " + rsadb + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rsadb rsadb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            rsadb = em.merge(rsadb);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = rsadb.getPerson();
                if (findRsadb(id) == null) {
                    throw new NonexistentEntityException("The rsadb with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rsadb rsadb;
            try {
                rsadb = em.getReference(Rsadb.class, id);
                rsadb.getPerson();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rsadb with id " + id + " no longer exists.", enfe);
            }
            em.remove(rsadb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rsadb> findRsadbEntities() {
        return findRsadbEntities(true, -1, -1);
    }

    public List<Rsadb> findRsadbEntities(int maxResults, int firstResult) {
        return findRsadbEntities(false, maxResults, firstResult);
    }

    private List<Rsadb> findRsadbEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rsadb.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Rsadb findRsadb(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rsadb.class, id);
        } finally {
            em.close();
        }
    }

    public int getRsadbCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rsadb> rt = cq.from(Rsadb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
