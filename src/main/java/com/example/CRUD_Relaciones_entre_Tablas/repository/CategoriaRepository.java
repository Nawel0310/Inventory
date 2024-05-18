package com.example.CRUD_Relaciones_entre_Tablas.repository;

import com.example.CRUD_Relaciones_entre_Tablas.entities.Categoria.Categoria;
import com.example.CRUD_Relaciones_entre_Tablas.entities.Marca.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    List<Categoria>findByMarca(Marca marca);
}
