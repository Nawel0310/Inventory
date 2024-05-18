package com.example.CRUD_Relaciones_entre_Tablas.repository;

import com.example.CRUD_Relaciones_entre_Tablas.entities.Marca.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<Marca,Integer> {
}
