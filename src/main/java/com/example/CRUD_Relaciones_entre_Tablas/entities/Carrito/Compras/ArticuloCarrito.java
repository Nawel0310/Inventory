package com.example.CRUD_Relaciones_entre_Tablas.entities.Carrito.Compras;


import com.example.CRUD_Relaciones_entre_Tablas.entities.Producto.Producto;
import com.example.CRUD_Relaciones_entre_Tablas.entities.Usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name="articulo_carrito")
public class ArticuloCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public ArticuloCarrito(Integer cantidad, Producto producto, Usuario usuario) {
        this.cantidad = cantidad;
        this.producto = producto;
        this.usuario = usuario;
    }

    public ArticuloCarrito(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ArticuloCarrito{" +
                "id=" + id +
                ", cantidad=" + cantidad +
                ", producto=" + producto +
                ", usuario=" + usuario +
                '}';
    }
}
