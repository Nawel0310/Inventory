package com.example.CRUD_Relaciones_entre_Tablas.entities.Producto;

import com.example.CRUD_Relaciones_entre_Tablas.entities.Categoria.Categoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length=148, nullable = false, unique=true)
    private String nombre;

    private float precio;
    @ManyToOne //Un producto va a pertenecer a MUCHAS categorias
    @JoinColumn(name="categoria_id")//Defino la columna con la que me voy a "unir"
    private Categoria categoria;

    public Producto(String nombre, float precio, Categoria categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public Producto(String nombre) {
        this.nombre = nombre;
    }
}
