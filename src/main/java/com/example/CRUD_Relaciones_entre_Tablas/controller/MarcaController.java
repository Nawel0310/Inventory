package com.example.CRUD_Relaciones_entre_Tablas.controller;

import com.example.CRUD_Relaciones_entre_Tablas.entities.Categoria.Categoria;
import com.example.CRUD_Relaciones_entre_Tablas.entities.Marca.Marca;
import com.example.CRUD_Relaciones_entre_Tablas.entities.Producto.Producto;
import com.example.CRUD_Relaciones_entre_Tablas.repository.CategoriaRepository;
import com.example.CRUD_Relaciones_entre_Tablas.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MarcaController {
    @Autowired
    private MarcaRepository marcaRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/marcas/nuevo")
    public String mostrarFormularioDeCrearNuevaMarca(Model modelo){
        List<Categoria> listaCategorias = categoriaRepository.findAll();
        modelo.addAttribute("listaCategorias",listaCategorias);
        modelo.addAttribute("marca",new Marca());
        return "marca_formulario";
    }

    @PostMapping("/marcas/nuevo")
    public String guardarMarca(Marca marca){
        marcaRepository.save(marca);
        return "redirect:/marcas";
    }

    @GetMapping("/marcas")
    public String listarMarcas(Model modelo){
        List<Marca> listaMarcas= marcaRepository.findAll();
        modelo.addAttribute("listaMarcas",listaMarcas);
        return "marcas";
    }

    @GetMapping("/marcas/editar/{id}")
    public String mostrarFormularioDeModificarMarca(@PathVariable("id") Integer id, Model modelo){
        List<Categoria>listaCategorias = categoriaRepository.findAll();
        Marca marca = marcaRepository.findById(id).get();

        modelo.addAttribute("marca",marca);
        modelo.addAttribute("listaCategorias",listaCategorias);
        return "marca_formulario";
    }

    @PostMapping("/marcas/editar/{id}")
    public String actualizarMarca(@PathVariable("id") Integer id, Marca marca){
        Marca marcaBD = marcaRepository.findById(id).get();

        marcaBD.setCategorias(marca.getCategorias());
        marcaBD.setNombre(marca.getNombre());

        marcaRepository.save(marcaBD);
        return "redirect:/marcas";
    }
    @GetMapping("/marcas/borrar/{id}")
    public String borrarMarca(@PathVariable Integer id){
        marcaRepository.deleteById(id);
        return "redirect:/marcas";
    }

}
