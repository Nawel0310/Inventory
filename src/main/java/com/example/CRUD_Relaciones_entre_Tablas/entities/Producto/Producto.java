package com.example.CRUD_Relaciones_entre_Tablas.entities.Producto;

import com.example.CRUD_Relaciones_entre_Tablas.entities.Categoria.Categoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    @ManyToOne //MUCHOS productos van a poder estar en UNA sola categoría
    @JoinColumn(name="categoria_id")//Defino la columna que va a representar la relacion con Categoria
    private Categoria categoria;

    @OneToMany (mappedBy = "producto",cascade = CascadeType.ALL) //Cascade permite que todos los hijos sean persistidos
    private List<ProductoDetalles> detalles = new ArrayList<>();

    public Producto(String nombre, float precio, Categoria categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public Producto(Integer id) {
        this.id = id;
    }

    public Producto(String nombre) {
        this.nombre = nombre;
    }

    public void anadirDetalles(String nombre, String valor){
        this.detalles.add(new ProductoDetalles(nombre,valor,this));
        //"this" hace referencia a la clase en donde estamos, en este caso Producto
    }

    public void setDetalles(Integer id, String nombre, String valor){
        this.detalles.add(new ProductoDetalles(nombre,valor,this));
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
