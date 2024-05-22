package com.example.CRUD_Relaciones_entre_Tablas.repository;

import com.example.CRUD_Relaciones_entre_Tablas.entities.Carrito.Compras.ArticuloCarrito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticuloCarritoRepository extends JpaRepository<ArticuloCarrito, Integer> {

}
