package com.example.CRUD_Relaciones_entre_Tablas.repository;

import com.example.CRUD_Relaciones_entre_Tablas.entities.Usuario.Rol;
import com.example.CRUD_Relaciones_entre_Tablas.entities.Usuario.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
//Permite ejecutar Test de una entidad
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
//Esta anotación me permite utilizar la misma configuración que la BD en producción
@Rollback(false)
//Esta anotación me permite indicar que las transacciones hechas durante los tests
//NO tendrán rollback, es decir, los cambios se mantendrán en la BD.
class UsuarioRepositoryTest {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void TestCrearRoles(){
        Rol rolAdmin = new Rol ("Administrador");
        Rol rolEdtior = new Rol ("Editor");
        Rol rolVisitante = new Rol("Visitante");

        testEntityManager.persist(rolAdmin);
        testEntityManager.persist(rolEdtior);
        testEntityManager.persist(rolVisitante);
    }

    @Test
    public void testCrearNuevoUsuarioConUnRol(){
        Rol rolAdmin = testEntityManager.find(Rol.class,1);
        Usuario usuario = new Usuario("admin@gmail.com","12345");

        usuario.setRol(rolAdmin);
        usuarioRepository.save(usuario);
    }

    @Test
    public void testCrearNuevoUsuarioConDosRoles(){
        Rol rolEditor = testEntityManager.find(Rol.class,2);
        Rol rolVisitante = testEntityManager.find(Rol.class,3);

        Usuario usuario = new Usuario("editorYVisiatante@gmail.com","6789");

        usuario.setRoles(new HashSet<>(Set.of(rolEditor,rolVisitante)));

       usuarioRepository.save(usuario);
    }

    @Test
    public void testAsignarRolAUsuarioExistente(){
        Rol rolEditor = testEntityManager.find(Rol.class,2);
        Usuario usuario = usuarioRepository.findById(1).get();

        usuario.setRol(rolEditor);
        usuarioRepository.save(usuario);
    }

    @Test
    public void testEliminarRolDeUnUsuarioExistente(){
        Usuario usuario = usuarioRepository.findById(1).get();
        Rol rolAEliminar = testEntityManager.find(Rol.class,2);

        usuario.eliminarRol(rolAEliminar);
    }

    @Test
    public void testCrearNuevoUsuarioConNuevoRol(){
        Rol rolVendedor = new Rol("Vendedor");
        Usuario usuario = new Usuario("vendedorNuevo22@gmail.com","121212");

        usuario.setRol(rolVendedor);

        usuarioRepository.save(usuario);
    }

    @Test
    public void testObtenerUsuario(){
        Usuario usuario = usuarioRepository.findById(1).get();
        testEntityManager.detach(usuario); //Significa que el entity manager ya no gestionara el usuario

        System.out.println(usuario.getEmail());
        System.out.println(usuario.getRoles()); //Puedo obtenerlo gracias al fetch
    }
    @Test
    public void testEliminarUsuario(){
        usuarioRepository.deleteById(2);
    }

}