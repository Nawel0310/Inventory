package com.example.CRUD_Relaciones_entre_Tablas.entities.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rol {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (length = 25, nullable = false, unique = true)
    private String nombre;

    public Rol (String nombre){
        this.nombre = nombre;
    }
    @Override
    public String toString(){
        return this.nombre;
    }

}
