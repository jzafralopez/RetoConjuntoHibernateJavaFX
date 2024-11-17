package org.conjunto.retoconjuntohibernatejavafx.dao;

import org.conjunto.retoconjuntohibernatejavafx.HibernateUtil;
import org.conjunto.retoconjuntohibernatejavafx.models.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

/** Esta clase es el DAO de Usuario  */

public class UserDAO implements DAO<Usuario> {

    public UserDAO() {
    }

    public static Usuario validarUsuario(String nombre, String pass) {
        Usuario usuario = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Usuario WHERE nombre = :nombre AND pass = :pass";
            Query<Usuario> query = session.createQuery(hql, Usuario.class);
            query.setParameter("nombre", nombre);
            query.setParameter("pass", pass);
            usuario = query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }


    @Override
    public List<Usuario> findAll() {
        return List.of();
    }

    @Override
    public Usuario findById(Integer id) {
        return null;
    }

    public boolean save(Usuario usuario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(usuario);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void update(Usuario usuario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Usuario usuarioExistente = session.get(Usuario.class, usuario.getId());
            if (usuarioExistente != null) {
                usuarioExistente.setNombre(usuario.getNombre());
                usuarioExistente.setPass(usuario.getPass());
                usuarioExistente.setEs_admin(usuario.isEs_admin());
                session.merge(usuarioExistente);
            }
            transaction.commit();
        }
    }


    @Override
    public boolean delete(Usuario usuario) {
        return false;
    }

    @Override
    public void deleteById(Integer id) {

    }
}