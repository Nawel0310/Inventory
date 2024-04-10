package com.example.CRUD_Relaciones_entre_Tablas;

import com.example.CRUD_Relaciones_entre_Tablas.entities.Categoria.Categoria;
import com.example.CRUD_Relaciones_entre_Tablas.repository.CategoriaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//Permite ejecutar Test de una entidad
@AutoConfigureTestDatabase (replace= AutoConfigureTestDatabase.Replace.NONE)
//Esta anotación me permite utilizar la misma configuración que la BD en producción
@Rollback(false)
//Esta anotación me permite indicar que las transacciones hechas durante los tests
//NO tendrán rollback, es decir, los cambios se mantendrán en la BD.
public class CategoriaRepositoryTest {
    @Autowired
    private CategoriaRepository repository;

    @Test
    public void testCrearCaegoria(){
        Categoria categoriaGuardada= repository.save(new Categoria("Electronicos"));
        assertThat(categoriaGuardada.getId()).isGreaterThan(0);
    }

}
