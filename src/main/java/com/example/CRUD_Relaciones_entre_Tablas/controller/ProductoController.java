package com.example.CRUD_Relaciones_entre_Tablas.controller;

import com.example.CRUD_Relaciones_entre_Tablas.entities.Categoria.Categoria;
import com.example.CRUD_Relaciones_entre_Tablas.entities.Producto.Producto;
import com.example.CRUD_Relaciones_entre_Tablas.repository.CategoriaRepository;
import com.example.CRUD_Relaciones_entre_Tablas.repository.ProductoRepository;
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

    @GetMapping("/productos")
    public String listarProductos(Model modelo){
        List<Producto> listaProductos=productoRepository.findAll();
        modelo.addAttribute("listaProductos",listaProductos);
        return "productos";
    }

    @GetMapping("/productos/nuevo")
    public String mostrarFormularioDeNuevoProducto(Model modelo){
        List<Categoria>listaCategorias = categoriaRepository.findAll();

        modelo.addAttribute("producto",new Producto());
        modelo.addAttribute("listaCategorias",listaCategorias);

        return "producto_formulario";
    }

    @PostMapping("/productos/nuevo")
    public String guardarProducto(Producto producto){
        productoRepository.save(producto);
        return "redirect:/productos";
    }

    @GetMapping("/productos/editar/{id}")
    public String mostrarFormularioDeModificarProducto(@PathVariable("id") Integer id, Model modelo){
        List<Categoria>listaCategorias = categoriaRepository.findAll();
        Producto producto= productoRepository.findById(id).get();

        modelo.addAttribute("producto",producto);
        modelo.addAttribute("listaCategorias",listaCategorias);
        return "producto_formulario";
    }

    @PostMapping("/productos/editar/{id}")
    public String actualizaProducto(@PathVariable("id") Integer id, Producto producto){
        Producto productoBD= productoRepository.findById(id).get();

        productoBD.setCategoria(producto.getCategoria());
        productoBD.setNombre(producto.getNombre());
        productoBD.setPrecio(producto.getPrecio());

        productoRepository.save(productoBD);

        return "redirect:/productos";
    }

    @GetMapping("/productos/borrar/{id}")
    public String borrarProductos(@PathVariable Integer id){
        productoRepository.deleteById(id);
        return "redirect:/productos";
    }

}
