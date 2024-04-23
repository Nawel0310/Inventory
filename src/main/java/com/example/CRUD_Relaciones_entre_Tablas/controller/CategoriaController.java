package com.example.CRUD_Relaciones_entre_Tablas.controller;

import com.example.CRUD_Relaciones_entre_Tablas.entities.Categoria.Categoria;
import com.example.CRUD_Relaciones_entre_Tablas.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/categorias")
    public String listarCategorias (Model modelo){
        List<Categoria> listaCategorias=categoriaRepository.findAll();
        modelo.addAttribute("listaCategorias",listaCategorias);
        return "categorias";
    }

    @GetMapping("/categorias/nuevo")
    public String mostrarFormularioDeNuevaCategoria (Model modelo){
        modelo.addAttribute("categoria",new Categoria());
        return "categoria_formulario";
    }

    @PostMapping("/categorias/guardar")
    public String guardarCategoria(Categoria categoria){
        if (categoria.getId()!=null){//Formulario de Edición, verifica que el ID sea procesado en la solicitud
            Categoria categoriaBD = categoriaRepository.findById(categoria.getId()).get();
            categoriaBD.setNombre(categoria.getNombre());
            categoriaRepository.save(categoriaBD);
        }
        else {
            categoriaRepository.save(categoria);
        }
        return "redirect:/categorias";
    }

    @GetMapping("/categorias/editar/{id}")
    public String editarCategoria(@PathVariable Integer id, Model model){
        Categoria categoriaBD= categoriaRepository.findById(id).get();
        model.addAttribute("categoria",categoriaBD);
        return "categoria_formulario";
    }

    @PostMapping("/categorias/borrar/{id}")
    public String borrarCategoria(@PathVariable Integer id){
        categoriaRepository.deleteById(id);
        return "redirect:/categorias";
    }

}
