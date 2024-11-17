package org.conjunto.retoconjuntohibernatejavafx;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/** Clase que se encarga de la configuración de Hibernate para que se maneje
 * en el resto de la app. */

public class HibernateUtil {
    public static SessionFactory sessionFactory;

    static{
        sessionFactory = new Configuration()
                .configure()
                .setProperty("hibernate.connection.username",System.getenv("hibernate_username"))
                .setProperty("hibernate.connection.password",System.getenv("hibernate_password"))
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

}

