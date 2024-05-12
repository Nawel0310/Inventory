package com.example.CRUD_Relaciones_entre_Tablas.entities.Producto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="producto_detalles")
public class ProductoDetalles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private String valor;

    @ManyToOne
    @JoinColumn(name="producto_id")
    private Producto producto;

    public ProductoDetalles(String nombre, String valor, Producto producto){
        this.nombre=nombre;
        this.valor=valor;
        this.producto = producto;
    }
    @Override
    public String toString(){
        return nombre + "-" + valor;
    }

}
