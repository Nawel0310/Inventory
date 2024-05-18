package com.example.CRUD_Relaciones_entre_Tablas.repository;

import com.example.CRUD_Relaciones_entre_Tablas.entities.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
