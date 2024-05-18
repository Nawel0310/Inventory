package com.example.CRUD_Relaciones_entre_Tablas.repository;

import com.example.CRUD_Relaciones_entre_Tablas.entities.Producto.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
