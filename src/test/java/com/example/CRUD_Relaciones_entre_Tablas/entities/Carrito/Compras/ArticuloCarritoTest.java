package com.example.CRUD_Relaciones_entre_Tablas.entities.Carrito.Compras;

import com.example.CRUD_Relaciones_entre_Tablas.entities.Producto.Producto;
import com.example.CRUD_Relaciones_entre_Tablas.entities.Usuario.Usuario;
import com.example.CRUD_Relaciones_entre_Tablas.repository.ArticuloCarritoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
//Permite ejecutar Test de una entidad
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
//Esta anotación me permite utilizar la misma configuración que la BD en producción
@Rollback(false)
//Esta anotación me permite indicar que las transacciones hechas durante los tests
//NO tendrán rollback, es decir, los cambios se mantendrán en la BD.
class ArticuloCarritoTest {
    @Autowired
    private ArticuloCarritoRepository articuloCarritoRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testAñadirArticulo(){
        Producto producto = testEntityManager.find(Producto.class,2);
        Usuario usuario = testEntityManager.find(Usuario.class, 1);

        ArticuloCarrito articulo = new ArticuloCarrito(1,producto,usuario);
        articuloCarritoRepository.save(articulo);
    }

    @Test
    public void testAñadirMultiplesArticulos(){
        Usuario usuario = testEntityManager.find(Usuario.class, 1);
        Producto producto1 = testEntityManager.find(Producto.class,2);
        Producto producto2 = testEntityManager.find(Producto.class,3);
        Producto producto3 = testEntityManager.find(Producto.class,4);

        ArticuloCarrito articulo1 = new ArticuloCarrito(7,producto1,usuario);
        ArticuloCarrito articulo2 = new ArticuloCarrito(5,producto2,usuario);
        ArticuloCarrito articulo3 = new ArticuloCarrito(3,producto3,usuario);

        articuloCarritoRepository.saveAll(List.of(articulo1,articulo2,articulo3));
    }

    @Test
    public void testListarArticulos(){
        List<ArticuloCarrito> listaArticulos = articuloCarritoRepository.findAll();

        listaArticulos.forEach(System.out::print);
    }

    @Test
    public void testActualizarArticulo(){
        ArticuloCarrito articuloCarrito = articuloCarritoRepository.findById(2).get();
        articuloCarrito.setCantidad(10);
    }

    @Test
    public void testeliminarArticulo(){
        articuloCarritoRepository.deleteById(1);
    }


}