package com.example.CRUD_Relaciones_entre_Tablas.entities.Marca;

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
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length=45, nullable = false, unique=true)
    private String nombre;

    @OneToMany //Una marca va a poder tener muchas categorias
    @JoinColumn(name = "marca_id")//Une con la columna categoria_id
    private List<Categoria> categorias = new ArrayList<>();
    public Marca(Integer id) {
        this.id = id;
    }
    public Marca(String nombre, List<Categoria> categorias) {
        this.nombre = nombre;
        this.categorias = categorias;
    }
}
