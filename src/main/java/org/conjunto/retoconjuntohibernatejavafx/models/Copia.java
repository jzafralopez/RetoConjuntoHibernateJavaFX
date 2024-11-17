// Copia.java
package org.conjunto.retoconjuntohibernatejavafx.models;

import jakarta.persistence.*;
import lombok.Data;

/** Clase que representa la tabla Copia de la base de datos. */

@Data
@Entity
@Table(name = "copia")
public class Copia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String estado;
    private String soporte;
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "id_pelicula", nullable = false)
    private Pelicula pelicula;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

}
