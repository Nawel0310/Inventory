package com.example.CRUD_Relaciones_entre_Tablas.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length=45, nullable = false, unique=true)
    private String nombre;

    public Categoria(String nombre) {
        this.nombre = nombre;
    }
}
