package org.conjunto.retoconjuntohibernatejavafx.dao;

import org.conjunto.retoconjuntohibernatejavafx.HibernateUtil;
import org.conjunto.retoconjuntohibernatejavafx.models.Pelicula;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

/** Esta clase es un DAO para la entidad Pelicula. */

public class PeliDAO implements DAO<Pelicula> {

    public PeliDAO() {
    }

    public List<Pelicula> findAll() {
        List<Pelicula> peliculas = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Pelicula";
            Query<Pelicula> query = session.createQuery(hql, Pelicula.class);

            peliculas = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return peliculas;
    }

    @Override
    public Pelicula findById(Integer id) {
        return null;
    }

    @Override
    public boolean save(Pelicula pelicula) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(pelicula);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public void update(Pelicula pelicula) {

    }

    @Override
    public boolean delete(Pelicula pelicula) {
        return false;
    }

    @Override
    public void deleteById(Integer id) {

    }

}

