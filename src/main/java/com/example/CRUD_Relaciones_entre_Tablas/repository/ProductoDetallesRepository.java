package com.example.CRUD_Relaciones_entre_Tablas.repository;

import com.example.CRUD_Relaciones_entre_Tablas.entities.Producto.ProductoDetalles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductoDetallesRepository extends JpaRepository<ProductoDetalles, Integer> {
    @Transactional
    void deleteByProductoId(Integer productoId);
}