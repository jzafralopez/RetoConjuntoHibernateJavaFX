package org.conjunto.retoconjuntohibernatejavafx.dao;

import org.conjunto.retoconjuntohibernatejavafx.HibernateUtil;
import org.conjunto.retoconjuntohibernatejavafx.models.Copia;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

/** Esta clase es un DAO para la entidad Copia. */

public class CopiaDAO {

    public List<Copia> findCopiasByUsuario(Integer idUsuario) {
        List<Copia> copias = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Copia WHERE usuario.id = " + idUsuario;
            Query<Copia> query = session.createQuery(hql, Copia.class);
            copias = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return copias;
    }

    public void borrarCopia(Copia copia) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.remove(copia);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addCopia(Copia copia) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(copia);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void actualizarCopia(Copia copia) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.merge(copia);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


}
