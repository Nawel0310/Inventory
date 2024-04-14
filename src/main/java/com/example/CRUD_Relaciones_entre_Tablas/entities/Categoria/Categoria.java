package com.example.CRUD_Relaciones_entre_Tablas.entities.Categoria;

import com.example.CRUD_Relaciones_entre_Tablas.entities.Marca.Marca;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    @JoinColumn(name = "marca_id")//Une con la columna marca_id
    private Marca marca;

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

}
