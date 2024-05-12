package com.example.CRUD_Relaciones_entre_Tablas.controller;

import com.example.CRUD_Relaciones_entre_Tablas.entities.Categoria.Categoria;
import com.example.CRUD_Relaciones_entre_Tablas.entities.Producto.Producto;
import com.example.CRUD_Relaciones_entre_Tablas.repository.CategoriaRepository;
import com.example.CRUD_Relaciones_entre_Tablas.repository.ProductoDetallesRepository;
import com.example.CRUD_Relaciones_entre_Tablas.repository.ProductoRepository;
import com.sun.net.httpserver.spi.HttpServerProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoDetallesRepository productoDetallesRepository;

    @GetMapping("/productos")
    public String listarProductos(Model modelo){
        List<Producto> listaProductos=productoRepository.findAll();
        modelo.addAttribute("listaProductos",listaProductos);
        return "productos";
    }

    @GetMapping("/productos/nuevo")
    public String nuevoProducto(Model modelo){
        List<Categoria>listaCategorias = categoriaRepository.findAll();

        modelo.addAttribute("producto",new Producto());
        modelo.addAttribute("listaCategorias",listaCategorias);

        return "producto_formulario";
    }

    @GetMapping("/productos/editar/{id}")
    public String editarProducto(@PathVariable Integer id, Model model){
        List<Categoria>listaCategorias = categoriaRepository.findAll();
        Producto productoBD= productoRepository.findById(id).get();

        model.addAttribute("producto",productoBD);
        model.addAttribute("listaCategorias",listaCategorias);
        return "producto_formulario";
    }

    @PostMapping("/productos/guardar")
    public String guardarProducto(Producto producto, HttpServletRequest request){
        String[] detallesIDs= request.getParameterValues("detallesID");
        String[] detallesNombre= request.getParameterValues("detallesNombre");
        String[] detallesValor= request.getParameterValues("detallesValor");


        if (producto.getId()!=null){//Formulario de Edición, verifica que el ID sea procesado en la solicitud
            Producto productoBD = productoRepository.findById(producto.getId()).get();

            productoBD.setPrecio(producto.getPrecio());
            productoBD.setCategoria(producto.getCategoria());
            productoBD.setNombre(producto.getNombre());

            productoDetallesRepository.deleteByProductoId(productoBD.getId());

            for (int i=0;i< detallesNombre.length;i++){
                productoBD.anadirDetalles(detallesNombre[i],detallesValor[i]);
            }

            productoRepository.save(productoBD);
        }
        else {//Formulario de nuevo producto
            for (int i=0;i< detallesNombre.length;i++){
                producto.anadirDetalles(detallesNombre[i],detallesValor[i]);
            }
            productoRepository.save(producto);
        }
        return "redirect:/productos";
    }

    @PostMapping("/productos/borrar/{id}")
    public String borrarProducto(@PathVariable Integer id){
        productoRepository.deleteById(id);
        return "redirect:/productos";
    }

}
