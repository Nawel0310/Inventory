package com.example.CRUD_Relaciones_entre_Tablas.entities.Usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false, unique = true)
    private String email;

    @Column(length = 10, nullable = false)
    private String password;

    @ManyToMany (cascade = CascadeType.PERSIST,fetch =FetchType.EAGER)
    //CascadeType.PERSIST:
    //Le indica que, cuando se persiste un objeto de la clase usuario,
    //tambien persisten los objetos contenidos dentro de Usuario.
    //=FetchType.EAGER
    //Le indica que cuando yo liste un objeto, tambien liste los objetos relacionados
    //EAGER: Apenas se invoca al objeto, se invocan sus objetos relacionados.
    //LAZY: Solo invoca el objeto y es necesario indicarle sus relacionados.
    @JoinTable(name = "usuario_rol",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name="rol_id"))
    private Set<Rol> roles = new HashSet<>();

    public Usuario (String email, String password, Set<Rol> roles){
        this.email=email;
        this.password=password;
        this.roles=roles;
    }

    public Usuario (String email, String password){
        this.email=email;
        this.password=password;
    }

    public void setRol (Rol rol){
        this.roles.add(rol);
    }

    public void eliminarRol(Rol rol){
        this.roles.remove(rol);
    }

}
