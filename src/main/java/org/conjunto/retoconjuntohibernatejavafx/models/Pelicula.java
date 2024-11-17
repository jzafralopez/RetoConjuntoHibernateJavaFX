package org.conjunto.retoconjuntohibernatejavafx.models;

import jakarta.persistence.*;
import lombok.Data;

/** Esta es la clase modelo de Película. */

@Data
@Entity
@Table(name = "pelicula")
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;
    private String genero;
    private int anho;
    private String descripcion;
    private String director;
    private String bso;
    private String poster;

}
