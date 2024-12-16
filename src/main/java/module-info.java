module org.conjunto.retoconjuntohibernatejavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires static lombok;
    requires jakarta.persistence;
    requires java.desktop;
    requires java.naming;
    requires net.sf.jasperreports.core;
    exports org.conjunto.retoconjuntohibernatejavafx;
    exports org.conjunto.retoconjuntohibernatejavafx.controllers to javafx.fxml;

    opens org.conjunto.retoconjuntohibernatejavafx.controllers to javafx.fxml;
    opens org.conjunto.retoconjuntohibernatejavafx to javafx.fxml;
    opens org.conjunto.retoconjuntohibernatejavafx.models to org.hibernate.orm.core, javafx.base;

}