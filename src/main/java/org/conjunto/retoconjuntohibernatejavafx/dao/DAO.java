package org.conjunto.retoconjuntohibernatejavafx.dao;

import java.util.List;

/** Este es el DAO genérico. */

public interface DAO<T> {
    List<T> findAll();
    T findById(Integer id);
    boolean save(T t);
    void update(T t);
    boolean delete(T t);
    void deleteById(Integer id);
}
