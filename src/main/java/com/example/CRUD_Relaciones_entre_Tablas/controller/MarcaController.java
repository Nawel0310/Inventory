package com.example.CRUD_Relaciones_entre_Tablas.controller;

import com.example.CRUD_Relaciones_entre_Tablas.entities.Categoria.Categoria;
import com.example.CRUD_Relaciones_entre_Tablas.entities.Marca.Marca;
import com.example.CRUD_Relaciones_entre_Tablas.repository.CategoriaRepository;
import com.example.CRUD_Relaciones_entre_Tablas.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MarcaController {
    @Autowired
    private MarcaRepository marcaRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/marcas/nueva")
    public String mostrarFormularioDeCrearNuevaMarca(Model modelo){
        List<Categoria> listaCategorias = categoriaRepository.findAll();
        modelo.addAttribute("listaCategorias",listaCategorias);
        modelo.addAttribute("marca",new Marca());
        return "marca_formulario";
    }



}
