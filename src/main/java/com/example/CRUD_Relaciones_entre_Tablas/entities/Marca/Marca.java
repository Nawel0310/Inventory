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
    @OneToMany(mappedBy = "marca", cascade = CascadeType.ALL)//Mapeado con el objeto "marca" de categoria
    private List<Categoria> categorias = new ArrayList<>();
    public Marca(Integer id) {
        this.id = id;
    }
    public Marca(String nombre, List<Categoria> categorias) {
        this.nombre = nombre;
        this.categorias = categorias;
    }
}
