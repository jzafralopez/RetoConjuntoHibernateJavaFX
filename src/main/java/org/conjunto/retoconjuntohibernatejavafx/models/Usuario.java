package org.conjunto.retoconjuntohibernatejavafx.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String pass;
    private boolean es_admin;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Usamos FetchType.LAZY para evitar problemas de carga
    private List<Copia> copias;
}
